package com.everest.database;

import com.everest.exception.FlightsNotFound;
import com.everest.model.Flight;
import com.everest.model.SortFlights;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataHandler {
    final File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");

    public List<Flight> readDataFromFolder() throws IOException {
        List<Flight> flightList = new ArrayList<>();
        if(folder.listFiles()==null) throw new FileNotFoundException();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            Optional<String> lines = Files.lines(Paths.get(fileEntry.getPath())).findFirst();
            String[] flightDetails = lines.get().split(",");
            Flight flight=new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]));
            flight.setSeats(flightDetails);
            flightList.add(flight);
        }
        flightList.sort(new SortFlights());
        return flightList;
    }

    public Flight readFlight(long number) throws IOException, FlightsNotFound {
        List<Flight> flightList = readDataFromFolder();
        List<Flight> flights=flightList.stream().filter(flight -> flight.getNumber()==number).collect(Collectors.toList());
        if(flights.size()==0) throw new FlightsNotFound();
        return flights.get(0);
    }

    public long createFlight(Flight flight) throws IOException {
        writeFileInFolder(flight);
        return flight.getNumber();
    }

    public boolean deleteFlight(long number) throws FlightsNotFound, IOException {
        if(folder.listFiles()==null) throw new FileNotFoundException();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.getName().equalsIgnoreCase(number + ".txt")) {
                return fileEntry.delete();
            }
        }
        throw new FlightsNotFound();
    }

    public Flight updateFlight(long number,Flight flight) throws IOException, FlightsNotFound {
        writeFileInFolder(flight);
        return readFlight(flight.getNumber());
    }

    private void writeFileInFolder(Flight newFlight) throws IOException {
        String[] flightDetails=newFlight.getFlightDetails().split(",");
        //System.out.println(flightDetails[6]+newFlight.getTotalSeats());
        String flightDetail=newFlight.getNumber() + "," + newFlight.getSource() + "," + newFlight.getDestination() + "," + newFlight.getDepartureDate() + "," + newFlight.getDepartureTime()+ "," + newFlight.getArrivalTime() + "," + Integer.parseInt(flightDetails[6]) + "," + Integer.parseInt(flightDetails[7]) + "," + Integer.parseInt(flightDetails[8]) + "," + Integer.parseInt(flightDetails[9]) + "," + Integer.parseInt(flightDetails[10]) + "," + Integer.parseInt(flightDetails[11]) + "," + Integer.parseInt(flightDetails[12]) + "," + Integer.parseInt(flightDetails[13]) + "," + Integer.parseInt(flightDetails[14]) + "," + Integer.parseInt(flightDetails[15]);
        Path pathName= Path.of(folder + File.separator + newFlight.getNumber() + ".txt");
        File file=new File(String.valueOf(pathName));
        if(!file.exists()) {
            file.createNewFile();
        }
        Files.writeString(pathName,flightDetail);
    }
}
