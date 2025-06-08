package com.worldline.fdm.stub.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.worldline.fdm.stub.model.FlightList;
import com.worldline.fdm.stub.model.SupplierFlight;
import com.worldline.fdm.stub.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v2/crazy-api")
@Slf4j
public class StubApiRestController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping(path = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlightList> getFlightData(
            @RequestParam(value = "from", required = true) String from, @RequestParam(value = "to", required = true) String to,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "outboundDate", required = true) LocalDateTime outboundDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "inboundDate", required = true) LocalDateTime inboundDate
    )  {
        FlightList list =  new FlightList(supplierRepository.getFilteredFlights(from, to, outboundDate, inboundDate));
        return new ResponseEntity<FlightList>(list, HttpStatus.OK);
    }

}
