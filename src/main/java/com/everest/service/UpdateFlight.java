package com.everest.service;

import com.everest.database.FileDriver;
import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class UpdateFlight {
    FileDriver fileDriver=new FileDriver();

    public boolean updateFlightData(List<Flight> modifiedFlights, int noOfPassengers, String flightType,String totalFare) throws IOException {
        boolean seatsOccupied=false;
        switch (flightType){
            case "Economic Class":
                if(modifiedFlights.get(0).getAvailableEconomicSeats()>=noOfPassengers) {
                    modifiedFlights.get(0).setAvailableEconomicSeats(noOfPassengers);
                    seatsOccupied=true;
                }
                break;
            case "First Class":
                if(modifiedFlights.get(0).getAvailableFirstClassSeats()>=noOfPassengers) {
                    modifiedFlights.get(0).setAvailableFirstClassSeats(noOfPassengers);
                    seatsOccupied = true;
                }
                break;
            case "Second Class":
                if(modifiedFlights.get(0).getAvailableSecondClassSeats()>=noOfPassengers) {
                    modifiedFlights.get(0).setAvailableSecondClassSeats(noOfPassengers);
                    seatsOccupied = true;
                }
                break;
        }
        modifiedFlights.get(0).setTotalFare(Double.parseDouble(totalFare));
        fileDriver.writeToFolder(modifiedFlights.get(0));
        return seatsOccupied;
    }
}
