package com.worldline.fdm.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonbPropertyOrder({"flights", "numberOfFlights"})
public class SupplierFlightList {
    /**
     * The Supplier list.
     */
    private List<SupplierFlight> flights;
    private int numberOfFlights;

    @Getter
    @Setter
    public static class SupplierFlight {
        private String carrier;
        private Double basePrice;
        private Double tax;
        private String departureAirportName;
        private String arrivalAirportName;

        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private LocalDateTime outboundDateTime;

        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private LocalDateTime inboundDateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"flights\":" + flights +
                "}";
    }
}
