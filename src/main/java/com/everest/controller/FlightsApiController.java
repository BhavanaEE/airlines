package com.everest.controller;
import com.everest.database.DataHandler;
import com.everest.exception.FlightsNotFound;
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
    public Flight getFlight(@PathVariable("number") long number) throws IOException, FlightsNotFound {
        return dataHandler.readFlight(number);
    }

    @PostMapping("/flights")
    public long create(@RequestBody Flight flight) throws IOException {
        return dataHandler.createFlight(flight);
    }

    @PutMapping("/flights/{number}")
    public Flight update(@PathVariable long number,@RequestBody Flight flight) throws IOException, FlightsNotFound {
        return dataHandler.updateFlight(number,flight);
    }

    @DeleteMapping("/flights/{number}")
    public boolean delete(@PathVariable("number") long number) throws IOException, FlightsNotFound {
        return dataHandler.deleteFlight(number);
    }
}
