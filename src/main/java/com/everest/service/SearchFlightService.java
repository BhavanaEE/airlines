package com.everest.service;

import com.everest.database.Data;
import com.everest.model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFlightService {
    public List<Flight> getFlights(String source, String destination,String departureDate){
        List<Flight> flightList = Data.flights.stream()
                .filter(flight -> flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
        return getFlightsByDepartureDate(flightList,departureDate);
    }
    public List<Flight> getFlightsByDepartureDate(List<Flight> flightList, String departureDate) {
        return flightList.stream().filter(flight -> flight.getDepartureDate().equals(LocalDate.parse(departureDate))).collect(Collectors.toList());
    }

}
