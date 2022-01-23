package com.everest.service;

import com.everest.dto.FlightDTO;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateFlight {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private FlightDTO flightDTO;

    public void updateFlightData(Flight selectedFlight, int noOfPassengers, double totalFare,String seatType) {
        selectedFlight.getSeatType(seatType).updateAvailableSeats(noOfPassengers);
        selectedFlight.setTotalFare(totalFare);
        flightDTO.updateFlight(selectedFlight);
    }
}