package com.everest.database;

import com.everest.model.Flight;
import com.everest.model.SortFlights;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataHandler {
    File folder = new File("/Users/bhavanachivukula/Training/airlines/src/main/java/com/everest/database/FlightsData");

    public List<Flight> readDataFromFolder() throws IOException {
        List<Flight> flightList = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            Stream<String> lines = Files.lines(Paths.get(fileEntry.getPath()));
            lines.map(line -> {
                String[] flightDetails = line.split(",");
                flightList.add(new Flight(Long.parseLong(flightDetails[0]), flightDetails[1], flightDetails[2], LocalDate.parse(flightDetails[3]), LocalTime.parse(flightDetails[4]), LocalTime.parse(flightDetails[5]), Integer.parseInt(flightDetails[6]), Integer.parseInt(flightDetails[7]), Integer.parseInt(flightDetails[8]), Integer.parseInt(flightDetails[9]), Integer.parseInt(flightDetails[10]), Integer.parseInt(flightDetails[11]), Integer.parseInt(flightDetails[12]), Integer.parseInt(flightDetails[13]), Integer.parseInt(flightDetails[14]), Integer.parseInt(flightDetails[15])));
                return line;
            }).collect(Collectors.toList());
        }
        Collections.sort(flightList,new SortFlights());
        return flightList;
    }

    public Flight readAFlight(long number) throws IOException {
        List<Flight> flightList = readDataFromFolder();
        try {
            for (Flight flight : flightList) {
                if (flight.getNumber() == number)
                    return flight;
            }
            throw new Exception();
        } catch (Exception e) {
            System.out.println("No flights with given flight number.");
        }
        return flightList.get(flightList.size()-1);
    }

    public long readLastFlight(String source,String destination) throws IOException {
        List<Flight> flightList=readDataFromFolder();
        Flight flight=flightList.get(flightList.size()-1);
        Flight newFlight=new Flight(Long.parseLong(String.valueOf(flight.getNumber()))+1,source,destination,LocalDate.of(2022,01,01),LocalTime.of(06,30),LocalTime.of(10,50),500,100,100,300,100,100,300,500,1000,800);
        writeFileInFolder(newFlight,source,destination);
        return newFlight.getNumber();
    }

    public boolean deleteFlight(long number) throws IOException {
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().equalsIgnoreCase(number + ".txt")) {
                return fileEntry.delete();
            }
        }
        return false;
    }

    public Flight updateAFlight(long number, String source, String destination) throws IOException {
        Flight flight=readAFlight(number);
        writeFileInFolder(flight,source,destination);
        return readAFlight(number);
    }

    private void writeFileInFolder(Flight newFlight,String source,String destination) throws IOException {
        String flightDetail=newFlight.getNumber() + "," + source + "," + destination + "," + newFlight.getDepartureDate() + "," + newFlight.getDepartureTime()+ "," + newFlight.getArrivalTime() + "," + newFlight.getTotalSeats() + "," + newFlight.getTotalEconomicSeats() + "," + newFlight.getTotalFirstClassSeats() + "," + newFlight.getTotalSecondClassSeats() + "," + newFlight.getAvailableEconomicSeats() + "," + newFlight.getAvailableFirstClassSeats() + "," + newFlight.getAvailableSecondClassSeats() + "," + newFlight.getEconomicBasePrice() + "," + newFlight.getFirstClassBasePrice() + "," + newFlight.getSecondClassBasePrice();
        Path pathName= Path.of(folder + File.separator + newFlight.getNumber() + ".txt");
        File file=new File(String.valueOf(pathName));
        file.createNewFile();
        Files.writeString(pathName,flightDetail);
    }
}
