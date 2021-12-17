package com.everest.controller;

import com.everest.service.SearchFlightService;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchFlightService searchFlightService;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String from, String to, String departureDate,String noOfPassengers,String flightType, Model model) throws Exception {
        List<Flight> flightList = searchFlightService.getFlights(from, to, departureDate);
        if (flightList.size() == 0) {
            model.addAttribute("errorMessage","No flights found");
            return "error";
        }
        model.addAttribute("flights", flightList);
        model.addAttribute("noOfPassengers",noOfPassengers);
        model.addAttribute("flightType",flightType);

       // searchFlightService.updateFlightData(flightList);
        return "search";
    }
}
