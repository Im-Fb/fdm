package com.worldline.fdm.repository;

import com.worldline.fdm.model.dao.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> getFilteredFlights(String airline, String departure, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime);
}
