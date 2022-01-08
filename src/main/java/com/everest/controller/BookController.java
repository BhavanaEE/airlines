package com.everest.controller;

import com.everest.exception.FlightsNotFound;
import com.everest.model.Flight;
import com.everest.service.UpdateFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.LimitExceededException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BookController {

    @Autowired
    private UpdateFlight updateFlight;

    @RequestMapping(value = "/booking")
    public String booking(String noOfPassengers,String flightNumber, String flightType,String totalFare, Model model) throws IOException, FlightsNotFound, LimitExceededException {
        File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");
        List<File> files = List.of(Objects.requireNonNull(folder.listFiles()));
        List<Flight> flightList = null;
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(flightNumber + ".txt")) {
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
                flightList = lines.map(line -> {
                    String[] flightDetails = line.split(",");
                    Flight flight=new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]));
                    flight.setSeats(flightDetails);
                    return flight;
                }).collect(Collectors.toList());
                break;
            }
        }
        if(flightList.get(0).getTotalSeats()<Integer.parseInt(noOfPassengers)) throw new LimitExceededException();
        updateFlight.updateFlightData(flightList.get(0),Integer.parseInt(noOfPassengers),Double.parseDouble(totalFare),flightType);
        model.addAttribute("flights",flightList);
        model.addAttribute("flightType",flightType);
        return "submit";
    }
}