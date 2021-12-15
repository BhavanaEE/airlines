package com.everest.service;

import com.everest.database.FileDriver;
import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchFlightService {
    FileDriver fileDriver=new FileDriver();
    File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");

    public List<Flight> searchFlightsBySourceAndDestination(String source, String destination) throws Exception {
        List<Flight> flights = fileDriver.readFromFolder(folder, source, destination);
        return flights;
    }

    public List<Flight> getFlights(String source, String destination, String departureDate) throws Exception {
        List<Flight> flightList = searchFlightsBySourceAndDestination(source, destination);
        flightList = getFlightsByDepartureDate(flightList, departureDate);
        flightList=getFlightsBySeatsAvailable(flightList);
        return getFlightsByFlightType(flightList);

    }

    private List<Flight> getFlightsByFlightType(List<Flight> flightList) {
        //System.out.println(flightType+"....");
        return flightList;
    }

    public List<Flight> getFlightsByDepartureDate(List<Flight> flightList, String departureDate) {
        List<Flight> flights = flightList.stream().filter(flight -> flight.getDepartureDate().equals(LocalDate.parse(departureDate))).collect(Collectors.toList());
        return flights;
    }

    public List<Flight> getFlightsBySeatsAvailable(List<Flight> flightList) {
        return flightList.stream().filter(flight -> flight.getAvailableSeats() >1).collect(Collectors.toList());
    }

    public boolean updateFlightData(List<Flight> modifiedFlights, int noOfPassengers,String flightType) throws IOException {
        int seatsAvailable = modifiedFlights.get(0).getAvailableSeats();
        boolean flag=false;
        if(modifiedFlights.get(0).getEconomicSeats()>noOfPassengers && modifiedFlights.get(0).getFirstClassSeats()>noOfPassengers && modifiedFlights.get(0).getSecondClassSeats()>noOfPassengers){
            if(flightType.equalsIgnoreCase("Economic"))
                modifiedFlights.get(0).setEconomicSeats(noOfPassengers);
            if(flightType.equalsIgnoreCase("First"))
                modifiedFlights.get(0).setFirstClassSeats(noOfPassengers);
            if(flightType.equalsIgnoreCase("Second"))
                modifiedFlights.get(0).setSecondClassSeats(noOfPassengers);
            modifiedFlights.get(0).setSeatsAvailable(seatsAvailable - noOfPassengers);
            flag=true;
            fileDriver.writeToFolder(modifiedFlights.get(0),flag);
        }
        return flag;
    }
}
