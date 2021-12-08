package com.everest.service;

import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchFlightService {
    public List<Flight> searchFlightsBySourceAndDestination(String source, String destination) throws IOException {
        List<Flight> flights = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/airline/database/Data.txt"));
        flights=lines
                .filter(line->{
                    String[] split = line.split(",");
                    return split[1].equalsIgnoreCase(source) && split[2].equalsIgnoreCase(destination);
                })
                .map(line->{
                    String[] flightDetails = line.split(",");
                    return new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]),Integer.parseInt(flightDetails[6]));
                })
                .collect(Collectors.toList());
        lines.close();
        return flights;
    }

    public List<Flight> getFlights(String source, String destination,String departureDate) throws IOException {
        List<Flight> flightList=searchFlightsBySourceAndDestination(source,destination);
        flightList = getFlightsByDepartureDate(flightList,departureDate);
        return getFlightsBySeatsAvailable(flightList);

    }

    public List<Flight> getFlightsByDepartureDate(List<Flight> flightList, String departureDate){
        List<Flight> flights = flightList.stream().filter(flight -> flight.getDepartureDate().equals(LocalDate.parse(departureDate))).collect(Collectors.toList());
        return flights;
    }
    public List<Flight> getFlightsBySeatsAvailable(List<Flight> flightList){
        return flightList.stream().filter(flight -> flight.getSeatsAvailable()>1).collect(Collectors.toList());
    }
    public void updateFlightData(List<Flight> modifiedFlights) throws IOException {
        for(int i=0;i<modifiedFlights.size();i++){
            if(modifiedFlights.get(i).getSeatsAvailable()>0) {
                writeToFile(modifiedFlights.get(i));
                break;
            }
        }
    }
    public void writeToFile(Flight modifiedFlights) throws IOException {
        List<String> flights = new ArrayList<>();
        Stream<String> lines = Files.lines(Paths.get("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/airline/database/Data.txt"));
        flights=lines
                .map(line->{
                    String[] flightDetails = line.split(",");
                    if(flightDetails[0].equals(String.valueOf(modifiedFlights.getNumber()))){
                        int seatsAvailable=modifiedFlights.getSeatsAvailable();
                        modifiedFlights.setSeatsAvailable(seatsAvailable-1);
                        line=flightDetails[0]+","+flightDetails[1]+","+flightDetails[2]+","+flightDetails[3]+","+flightDetails[4]+","+flightDetails[5]+","+modifiedFlights.getSeatsAvailable();
                    }
                    return line;
                })
                .collect(Collectors.toList());
        Files.write(Path.of("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/airline/database/Data.txt"),flights);
        lines.close();
    }
}
