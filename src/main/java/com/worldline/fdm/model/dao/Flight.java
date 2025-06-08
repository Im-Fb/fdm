package com.worldline.fdm.model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
        name = "Flight.getFilteredFlights",
        query = "SELECT f FROM Flight f WHERE lower(f.airline) like CONCAT('%',?1,'%') " +
                                        "AND f.departure = upper(?2) AND f.destination = upper(?3) " +
                                        "AND f.departureDateTime >= ?4  AND f.arrivalDateTime <= ?5")
@Table(name = "FLIGHT")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Flight {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Size(max = 100, message = "The length of full name must be between 2 and 100 characters.")
    @Column(name = "AIRLINE_NAME")
    private String airline;

    @Column(name = "SUPPLIER")
    private String supplier;

    @Column(name = "FARE")
    private Double price;

    @NotEmpty(message = "The departure is required.")
    @Size(min = 3, max = 3, message = "The length of departure must be 3 characters.")
    @Column(name = "DEPATURE")
    private String departure;

    @NotEmpty(message = "The destination is required.")
    @Size(min = 3, max = 3, message = "The length of destination must be 3 characters.")
    @Column(name = "DESTINATION")
    private String destination;

    @FutureOrPresent(message = "The departure date time must be today or in the future.")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "DEPATURE_TIME")
    private LocalDateTime departureDateTime;

    @FutureOrPresent(message = "The arrival date time must be today or in the future.")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime arrivalDateTime;
}
