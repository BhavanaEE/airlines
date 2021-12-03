package com.everest.controller;

import com.everest.database.Data;
import com.everest.model.Flight;
import com.everest.service.SearchFlightService;
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
    public String search(String from, String to, String departureDate,Model model) {
        List<Flight> flightList = searchFlightService.getFlights(from, to, departureDate);
        model.addAttribute("flights", flightList);
        return "search";
    }
}
