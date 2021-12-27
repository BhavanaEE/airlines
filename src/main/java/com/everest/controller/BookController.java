package com.everest.controller;

import com.everest.service.PriceStrategy;
import com.everest.model.Flight;
import com.everest.service.UpdateFlight;
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
    private UpdateFlight updateFlight;

    @Autowired
    private PriceStrategy priceStrategy;

    @RequestMapping(value = "/booking")
    public String booking(String noOfPassengers,String flightNumber, String flightType,String totalFare, Model model) throws IOException {
        File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");
        List<File> files = List.of(folder.listFiles());
        List<Flight> flightList = null;
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(flightNumber + ".txt")) {
                Stream<String> lines = Files.lines(Paths.get(file.getPath()));
                flightList = lines.map(line -> {
                    String[] flightDetails = line.split(",");
                    return new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6]),Integer.parseInt(flightDetails[7]), Integer.parseInt(flightDetails[8]), Integer.parseInt(flightDetails[9]),Integer.parseInt(flightDetails[10]), Integer.parseInt(flightDetails[11]), Integer.parseInt(flightDetails[12]), Integer.parseInt(flightDetails[13]), Integer.parseInt(flightDetails[14]), Integer.parseInt(flightDetails[15]));
                }).collect(Collectors.toList());
                break;
            }
        }
        try{
            if(flightList.get(0).getTotalSeats()<Integer.parseInt(noOfPassengers))
                throw new Exception();
        }
        catch (Exception e){
            return "noseats";
        }
        if(updateFlight.updateFlightData(flightList,Integer.parseInt(noOfPassengers),flightType,totalFare)){
            model.addAttribute("flights",flightList);
            model.addAttribute("flightType",flightType);
            return "submit";
        }
        return "noseats";
    }
}