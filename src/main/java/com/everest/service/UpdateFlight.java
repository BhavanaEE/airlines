package com.everest.service;

import com.everest.database.FileDriver;
import com.everest.exception.FlightsNotFound;
import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UpdateFlight {
    FileDriver fileDriver = new FileDriver();

    public void updateFlightData(Flight selectedFlight, int noOfPassengers, double totalFare,String seatType) throws IOException, FlightsNotFound {
        if(selectedFlight.getSeatType(seatType).getAvailableSeats()<noOfPassengers) throw new FlightsNotFound();
        selectedFlight.getSeatType(seatType).setAvailableSeats(noOfPassengers);
        selectedFlight.setTotalFare(totalFare);
        fileDriver.writeToFolder(selectedFlight);
    }
}