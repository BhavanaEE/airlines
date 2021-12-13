package com.everest.controller;

import com.everest.service.SearchFlightService;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BookController {

    @Autowired
    private SearchFlightService searchFlightService;

    @RequestMapping(value = "/booking")
    public String booking(String noOfPassengers, String flightNumber, Model model) throws IOException {
        boolean flag=false;
        File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");
        List<File> files = List.of(folder.listFiles());
        List<Flight> flightList = null;
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(flightNumber + ".txt")) {
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
                flightList = lines.map(line -> {
                    String[] flightDetails = line.split(",");
                    return new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6]));
                }).collect(Collectors.toList());
                break;
            }
        }
        searchFlightService.updateFlightData(flightList,Integer.parseInt(noOfPassengers));
        return "home";
    }
}