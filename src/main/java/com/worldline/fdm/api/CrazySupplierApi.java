package com.worldline.fdm.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldline.fdm.exception.FdmException;
import com.worldline.fdm.model.SupplierFlightList;
import com.worldline.fdm.model.dao.Flight;
import com.worldline.fdm.utils.DataConverter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CommonsLog
public class CrazySupplierApi {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${crazy-supplier-api.url}")
    private String url;

    /**
     * retreiveFlightsData from supplier API
     * @param traceId  traceId
     * @param from Source
     * @param to destination
     * @param outboundDate depature time
     * @param inboundDate arrival time
     * @return flight data or empty list
     */
    public List<Flight> retreiveFlightsData(String traceId, String from, String to, LocalDateTime outboundDate, LocalDateTime inboundDate) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("outboundDate", outboundDate)
                .queryParam("inboundDate", inboundDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<Flight> flightList = new ArrayList<>();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

            if (response.getStatusCodeValue() != 200) {
                log.error(String.format("Unable to fetch Crazy supplier flight data. traceId: %s", traceId));
                return flightList;

            }

            SupplierFlightList supplierFlightList = objectMapper.readValue(response.getBody(), SupplierFlightList.class);
            supplierFlightList.getFlights()
                                .forEach(flight -> {
                                    flightList.add(DataConverter.toFlightData(flight));
            });

            return flightList;

        } catch (HttpServerErrorException | JsonProcessingException e) {
            log.error(String.format("Unable to process Crazy supplier flight data. traceId: %s error: %s", traceId, e.getMessage()));
            return flightList;
        }

    }

}
