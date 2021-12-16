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
public class SearchController {

    @Autowired
    private SearchFlightService searchFlightService;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String from, String to, String departureDate, Model model) throws Exception {
        List<Flight> flightList = searchFlightService.getFlights(from, to, departureDate);
        if (flightList.size() == 0) {
            model.addAttribute("errorMessage","No flights found");
            return "error";
        }
        model.addAttribute("flights", flightList);
       // searchFlightService.updateFlightData(flightList);
        return "search";
    }

    @RequestMapping(value = "/book")
    public String book(String flightNumber, Model model) throws IOException {
        File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");
        List<File> files = List.of(folder.listFiles());
        List<Flight> flightList = null;
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(flightNumber + ".txt")) {
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
                flightList = lines.map(line -> {
                    String[] flightDetails = line.split(",");
                    return new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6]),Integer.parseInt(flightDetails[7]), Integer.parseInt(flightDetails[8]), Integer.parseInt(flightDetails[9]), Integer.parseInt(flightDetails[10]), Integer.parseInt(flightDetails[11]), Integer.parseInt(flightDetails[12]));
                }).collect(Collectors.toList());
                break;
            }
        }
        model.addAttribute("flights", flightList);
        return "booking";

    }
}
