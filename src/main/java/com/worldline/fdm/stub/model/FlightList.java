package com.worldline.fdm.stub.model;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.ArrayList;
import java.util.Collection;

@JsonbPropertyOrder({"flightList", "numberOfFlights"})
public class FlightList {
    /**
     * The Supplier list.
     */
    private final Collection<SupplierFlight> flightList;


    /**
     * Instantiates a new Supplier list.
     *
     */
    public FlightList() {
        flightList = new ArrayList<>();
    }

    /**
     * Instantiates a new Supplier list.
     *
     * @param suppliers the involved supplier
     *
     */
    public FlightList(Collection<SupplierFlight> suppliers) {
        this.flightList = suppliers;

    }

    /**
     * Gets suppliers.
     *
     * @return the suppliers
     */
    public Collection<SupplierFlight> getFlights() {
        return flightList;
    }


    /**
     * Gets total number of suppliers.
     *
     * @return the total number suppliers
     */
    public int getNumberOfFlights() {
        return (null != flightList) ? flightList.size() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "\"flightList\":" + flightList +
                "}";
    }
}
