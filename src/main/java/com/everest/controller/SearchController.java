package com.everest.controller;

import com.everest.dto.FlightDTO;
import com.everest.exception.FlightsNotFound;
import com.everest.service.PriceStrategy;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private FlightDTO flightDTO;

    @Autowired
    private PriceStrategy priceStrategy;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String from, String to, String departureDate,String noOfPassengers,String seatType, Model model) throws Exception {
        List<Flight> flightList = flightDTO.getFlights(from, to, departureDate,noOfPassengers,seatType);
        if(flightList.size()==0) throw new FlightsNotFound();
        priceStrategy.calculateFareForEachFlight(flightList,seatType,Integer.parseInt(noOfPassengers));
        model.addAttribute("flights", flightList);
        model.addAttribute("noOfPassengers",noOfPassengers);
        model.addAttribute("seatType",seatType);
        return "search";
    }
}
