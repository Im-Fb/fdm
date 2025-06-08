package com.worldline.fdm.api;

import com.worldline.fdm.exception.FdmException;
import com.worldline.fdm.model.FlightList;
import com.worldline.fdm.model.dao.Flight;
import com.worldline.fdm.repository.FlightRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("v1")

@Slf4j
public class ApiRestController {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CrazySupplierApi supplierApi;

    /**
     * Fetch filtered flight data
     * @param request Flight filter conditions
     * @return list of flights data
     * @throws FdmException
     */
    @PostMapping(path = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightList> getFlightData(@Valid @RequestBody Flight request) throws FdmException {

        String traceId = UUID.randomUUID().toString();
        FlightList flightList = new FlightList(flightRepository.getFilteredFlights(request.getAirline(),
                                        request.getDeparture(), request.getDestination(),
                                        request.getDepartureDateTime(), request.getArrivalDateTime()));
        flightList.addAllFlights(supplierApi.retreiveFlightsData(traceId, request.getDeparture(),request.getDestination(),
                request.getDepartureDateTime(), request.getArrivalDateTime()));

        return new ResponseEntity<FlightList>(flightList, HttpStatus.OK);
    }

    /**
     * Create new flight data
     * @param request new Flight data
     * @return created Flight data
     * @throws FdmException
     */
    @PostMapping(path = "/flight", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight request) throws FdmException {

        request.setId((int) (flightRepository.count()+1));
        return new ResponseEntity<Flight>(flightRepository.saveAndFlush(request),HttpStatus.CREATED);

    }

    /**
     * Update existing filght data by Id
     * @param id unique id
     * @param request flight data to be udpated
     * @return updated flight data
     */
    @PatchMapping(path = "/flight/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id,
                                               @Valid @RequestBody Flight request)  {

        Flight flight = flightRepository.findById(id).orElse(null);
        if(null!=flight){
            request.setId(id);
            flightRepository.save(request);
            return new ResponseEntity<Flight>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete flight data present in the DB
     *
     * @param id delete flight data by id
     * @return deleted flight data
     */
    @DeleteMapping(path = "/flight/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> deleteEntry(@PathVariable Integer id)  {

        Flight flight = flightRepository.findById(id).orElse(null);
        if(null!=flight){
            flightRepository.delete(flight);
            return new ResponseEntity<Flight>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
