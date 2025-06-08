package com.worldline.fdm;

import com.worldline.fdm.model.SupplierFlightList;
import com.worldline.fdm.model.dao.Flight;
import com.worldline.fdm.utils.DataConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataConverterTest {

    @Test
    void converterTest() {
        SupplierFlightList.SupplierFlight flight = new SupplierFlightList.SupplierFlight();
        flight.setBasePrice(10.0);
        flight.setTax(10.0);
        Flight flight1 =DataConverter.toFlightData(flight);
        assertEquals(20.0, flight1.getPrice());
    }
}
