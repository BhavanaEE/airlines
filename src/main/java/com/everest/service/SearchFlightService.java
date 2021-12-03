package com.everest.service;

import com.everest.database.Data;
import com.everest.model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFlightService {
    public List<Flight> getFlights(String source, String destination){
        return Data.flights.stream()
                .filter(flight -> flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }
}
