package com.everest.service;

import com.everest.database.FileDriver;
import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchFlight {

    FileDriver fileDriver=new FileDriver();
    File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");

    public List<Flight> searchFlightsBySourceAndDestination(String source, String destination) throws Exception {
        return fileDriver.readFromFolder(folder, source, destination);
    }

    public List<Flight> getFlights(String source, String destination, String departureDate) throws Exception {
        List<Flight> flightList = searchFlightsBySourceAndDestination(source, destination);
        flightList = getFlightsByDepartureDate(flightList, departureDate);
        flightList=getFlightsBySeatsAvailable(flightList);
        return flightList;
    }

    public List<Flight> getFlightsByDepartureDate(List<Flight> flightList, String departureDate) {
        List<Flight> flights = flightList.stream().filter(flight -> flight.getDepartureDate().equals(LocalDate.parse(departureDate))).collect(Collectors.toList());
        return flights;
    }

    public List<Flight> getFlightsBySeatsAvailable(List<Flight> flightList) {
        return flightList.stream().filter(flight -> flight.getTotalSeats() >1).collect(Collectors.toList());
    }

}
