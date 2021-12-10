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
    File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");
    public List<Flight> readFromFolder(File folder, String source, String destination) throws IOException {
        List<Flight> flights = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                readFromFolder(fileEntry, source, destination);
            } else {
                Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
                lines
                        .filter(line -> {
                            String[] split = line.split(",");
                            return split[1].equalsIgnoreCase(source) && split[2].equalsIgnoreCase(destination);
                        })
                        .map(line -> {
                            String[] flightDetails = line.split(",");
                            flights.add(new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6])));
                            return line;
                        })
                        .collect(Collectors.toList());
            }
        }
        return flights;
    }

    public List<Flight> searchFlightsBySourceAndDestination(String source, String destination) throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights = readFromFolder(folder, source, destination);
        return flights;
    }

    public List<Flight> getFlights(String source, String destination, String departureDate) throws Exception {
        List<Flight> flightList = searchFlightsBySourceAndDestination(source, destination);
        flightList.forEach(System.out::println);
        flightList = getFlightsByDepartureDate(flightList, departureDate);
        return getFlightsBySeatsAvailable(flightList);

    }

    public List<Flight> getFlightsByDepartureDate(List<Flight> flightList, String departureDate) {
        List<Flight> flights = flightList.stream().filter(flight -> flight.getDepartureDate().equals(LocalDate.parse(departureDate))).collect(Collectors.toList());
        return flights;
    }

    public List<Flight> getFlightsBySeatsAvailable(List<Flight> flightList) {
        return flightList.stream().filter(flight -> flight.getSeatsAvailable() >1).collect(Collectors.toList());
    }

    public void updateFlightData(List<Flight> modifiedFlights) throws IOException {
        int seatsAvailable = modifiedFlights.get(0).getSeatsAvailable();
        modifiedFlights.get(0).setSeatsAvailable(seatsAvailable - 1);
        writeToFile(modifiedFlights.get(0));
    }

    public void writeToFile(Flight modifiedFlights) throws IOException {
        List<String> flights = new ArrayList<>();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            flights=lines
                    .map(line -> {
                        String[] flightDetails = line.split(",");
                        if (flightDetails[0].equals(String.valueOf(modifiedFlights.getNumber()))) {
                            line = flightDetails[0] + "," + flightDetails[1] + "," + flightDetails[2] + "," + flightDetails[3] + "," + flightDetails[4] + "," + flightDetails[5] + "," + modifiedFlights.getSeatsAvailable();
                        }
                        return line;
                    })
                    .collect(Collectors.toList());
            Files.write(Path.of(fileEntry.getPath()), flights);
        }
    }
}
