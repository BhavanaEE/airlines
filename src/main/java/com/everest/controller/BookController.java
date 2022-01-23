package com.everest.controller;

import com.everest.dto.FlightDTO;
import com.everest.exception.FlightsNotFound;
import com.everest.model.Flight;
import com.everest.service.UpdateFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class BookController {

    @Autowired
    private FlightDTO flightDTO;
    @Autowired
    private UpdateFlight updateFlight;

    @RequestMapping(value = "/booking")
    public String booking(String noOfPassengers,String flightNumber, String seatType,String totalFare, Model model) throws IOException, FlightsNotFound {
        Flight flight=flightDTO.getFlightByFlightNumber(flightNumber);
        updateFlight.updateFlightData(flight,Integer.parseInt(noOfPassengers),Double.parseDouble(totalFare),seatType);
        model.addAttribute("flights",flight);
        model.addAttribute("seatType",seatType);
        return "submit";
    }
}