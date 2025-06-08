package com.worldline.fdm.utils;

import com.worldline.fdm.model.SupplierFlightList;
import com.worldline.fdm.model.dao.Flight;

public class DataConverter {

    private DataConverter() {
        throw new IllegalStateException("Converter class");
    }

    public static Flight toFlightData(SupplierFlightList.SupplierFlight supplierFlight) {
        Flight flight = new Flight();
        flight.setAirline(supplierFlight.getCarrier());
        flight.setDeparture(supplierFlight.getDepartureAirportName());
        flight.setDestination(supplierFlight.getArrivalAirportName());
        flight.setDepartureDateTime(supplierFlight.getOutboundDateTime());
        flight.setArrivalDateTime(supplierFlight.getOutboundDateTime());
        flight.setPrice(Double.sum(supplierFlight.getBasePrice(), supplierFlight.getTax()));
        return flight;
    }
}
