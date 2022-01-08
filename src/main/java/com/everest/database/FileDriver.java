package com.everest.database;

import com.everest.model.Flight;
import com.everest.model.SeatType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

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
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            lines
                    .filter(line -> {
                        String[] split = line.split(",");
                        return split[1].equalsIgnoreCase(source) && split[2].equalsIgnoreCase(destination);
                    })
                    .map(line -> {
                        String[] flightDetails = line.split(",");
                        Flight flight = new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]));
                        flight.setSeats(flightDetails);
                        flights.add(flight);
                        return line;
                    })
                    .collect(Collectors.toList());
        }
        return flights;
    }

    public void writeToFolder(Flight modifiedFlight) throws IOException {
        List<String> flights;
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            flights = lines
                    .map(line -> {
                        String[] flightDetails = line.split(",");
                        if (flightDetails[0].equals(String.valueOf(modifiedFlight.getNumber()))) {
                            line = modifiedFlight.getFlightDetails();
                        }
                        return line;
                    })
                    .collect(Collectors.toList());
            Files.write(Path.of(fileEntry.getPath()), flights);
        }
    }
}
