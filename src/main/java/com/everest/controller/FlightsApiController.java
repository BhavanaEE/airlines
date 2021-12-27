package com.everest.controller;

import com.everest.database.DataHandler;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FlightsApiController {

    @Autowired
    private DataHandler dataHandler;

    @GetMapping("/flights")
    public List<Flight> getAllFlights() throws IOException {
        return dataHandler.readDataFromFolder();
    }

    @GetMapping("/flights/{number}")
    public Flight getAFlight(@PathVariable("number") long number) throws IOException {
        return dataHandler.readAFlight(number);
    }

    @PostMapping("/flights")
    public long createAFlight(String source, String destination) throws IOException {
        return dataHandler.readLastFlight(source,destination);
    }

    @PutMapping("/flights/{number}")
    public Flight update(@PathVariable long number, String source, String destination) throws IOException {
        return dataHandler.updateAFlight(number,source,destination);
    }

    @DeleteMapping("/flights/{number}")
    public boolean deleteFlight(@PathVariable("number") long number) throws IOException {
        return dataHandler.deleteFlight(number);
    }
}
