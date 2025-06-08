package com.worldline.fdm.stub.repository;

import com.worldline.fdm.stub.model.SupplierFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierFlight,Integer> {
    List<SupplierFlight> getFilteredFlights(String departure, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime);
}
