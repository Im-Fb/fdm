package com.worldline.fdm.stub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@NamedQuery(
        name = "SupplierFlight.getFilteredFlights",
        query = "SELECT s FROM SupplierFlight s WHERE s.departureAirportName = upper(?1) AND s.arrivalAirportName = upper(?2) " +
                                        "AND s.outboundDateTime >= ?3  AND s.inboundDateTime <= ?4" )
@Table(name = "SUPPLIER_FLIGHT")

public class SupplierFlight {
    @JsonIgnore
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "CARRIER_NAME")
    private String carrier;

    @Column(name = "BASE_PRICE")
    private Double basePrice;

    @Column(name = "TAX")
    private Double tax;

    @Column(name = "DEPATURE")
    private String departureAirportName;

    @Column(name = "ARIVAL")
    private String arrivalAirportName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "OUTBOUND_TIME")
    private LocalDateTime outboundDateTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "INBOUND_TIME")
    private LocalDateTime inboundDateTime;
}
