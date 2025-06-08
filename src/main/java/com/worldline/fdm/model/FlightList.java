package com.worldline.fdm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.worldline.fdm.model.dao.Flight;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.ArrayList;
import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonbPropertyOrder({"flightList", "numberOfFlights"})
public class FlightList {
    /**
     * The Flight list.
     */
    private final Collection<Flight> flightList;


    /**
     * Instantiates a new Flight list.
     *
     */
    public FlightList() {
        flightList = new ArrayList<>();
    }

    /**
     * Instantiates a new Flight list.
     *
     * @param flights the involved flight
     *
     */
    public FlightList(Collection<Flight> flights) {
        this.flightList = flights;

    }

    /**
     * Gets flights.
     *
     * @return the flights
     */
    public Collection<Flight> getFlights() {
        return flightList;
    }


    /**
     * Gets total number of flights.
     *
     * @return the total number flights
     */
    public int getNumberOfFlights() {
        return (null != flightList) ? flightList.size() : 0;
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

    public void addAllFlights(Collection<Flight> flights) {
        flightList.addAll(flights);
    }

    @Override
    public String toString() {
        return "{" +
                "\"flightList\":" + flightList +
                "}";
    }
}
