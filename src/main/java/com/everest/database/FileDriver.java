package com.everest.database;

import com.everest.model.Flight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDriver {
    File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");

    public List<Flight> readFromFolder(File folder, String source, String destination) throws IOException {
        List<Flight> flights = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            lines
                    .filter(line -> {
                        String[] split = line.split(",");
                        return split[1].equalsIgnoreCase(source) && split[2].equalsIgnoreCase(destination);
                    })
                    .map(line -> {
                        String[] flightDetails = line.split(",");
                        flights.add(new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6]), Integer.parseInt(flightDetails[7]), Integer.parseInt(flightDetails[8]), Integer.parseInt(flightDetails[9]), Integer.parseInt(flightDetails[10]), Integer.parseInt(flightDetails[11]), Integer.parseInt(flightDetails[12]), Integer.parseInt(flightDetails[13]), Integer.parseInt(flightDetails[14]), Integer.parseInt(flightDetails[15])));
                        return line;
                    })
                    .collect(Collectors.toList());
        }
        return flights;
    }

    public void writeToFolder(Flight modifiedFlights) throws IOException {
        List<String> flights = new ArrayList<>();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            flights = lines
                    .map(line -> {
                        String[] flightDetails = line.split(",");
                        if (flightDetails[0].equals(String.valueOf(modifiedFlights.getNumber()))) {
                            line = flightDetails[0] + "," + flightDetails[1] + "," + flightDetails[2] + "," + flightDetails[3] + "," + flightDetails[4] + "," + flightDetails[5] + "," + modifiedFlights.getTotalSeats() + "," + modifiedFlights.getTotalEconomicSeats() + "," + modifiedFlights.getTotalFirstClassSeats() + "," + modifiedFlights.getTotalSecondClassSeats() + "," + modifiedFlights.getAvailableEconomicSeats() + "," + modifiedFlights.getAvailableFirstClassSeats() + "," + modifiedFlights.getAvailableSecondClassSeats() + "," + modifiedFlights.getEconomicBasePrice() + "," + modifiedFlights.getFirstClassBasePrice() + "," + modifiedFlights.getSecondClassBasePrice();
                        }
                        return line;
                    })
                    .collect(Collectors.toList());
            Files.write(Path.of(fileEntry.getPath()), flights);
        }
    }
}
