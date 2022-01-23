package com.everest.dto;

import com.everest.FlightRowMapper;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FlightDTO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Flight> getFlights(String source, String destination, String departureDate,String noOfPassengers,String seatType){
        Map<String ,Object> map=new HashMap<>();
        map.put("source",source);
        map.put("destination",destination);
        map.put("departureDate", LocalDate.parse(departureDate));
        List<Flight> flights= jdbcTemplate.query("SELECT * FROM flight_database.Flight f join flight_database.FlightDetail fd  on f.`Number` = fd.`Number` and f.Source=:source and f.Destination=:destination and f.DepartureDate=:departureDate",map,new FlightRowMapper());
        return flights.stream().filter(flight -> flight.getSeatType(seatType).getAvailableSeats()>=Integer.parseInt(noOfPassengers)).collect(Collectors.toList());
    }

    public Flight getFlightByFlightNumber(String number){
        Map<String ,Object> map=new HashMap<>();
        map.put("number",Long.parseLong(number));
        return jdbcTemplate.queryForObject("SELECT * FROM flight_database.Flight f join flight_database.FlightDetail fd  on f.`Number` =fd.`Number` and f.`Number` =:number", map, new FlightRowMapper());
    }

    public void updateFlight(Flight modifiedFlight){
        Map<String ,Object> map=getFlightDetails(modifiedFlight);
        jdbcTemplate.update("UPDATE flight_database.FlightDetail fd set fd.TotalEconomicSeats=:totalEconomicSeats,fd.AvailableEconomicSeats=:availableEconomicSeats,fd.EconomicBasePrice=:economicBasePrice,fd.TotalFirstClassSeats=:totalFirstClassSeats,fd.AvailableFirstClassSeats=:availableFirstClassSeats,fd.FirstClassBasePrice=:firstClassBasePrice,fd.TotalSecondClassSeats=:totalSecondClassSeats,fd.AvailableSecondClassSeats=:availableSecondClassSeats,fd.SecondClassBasePrice=:secondClassBasePrice where fd.Number=:number",map);
    }

    public Map<String ,Object> getFlightDetails(Flight flight){
        Map<String ,Object> map=new HashMap<>();
        map.put("number",flight.getNumber());
        map.put("source",flight.getSource());
        map.put("destination",flight.getDestination());
        map.put("departureDate",flight.getDepartureDate());
        map.put("departureTime",flight.getDepartureTime());
        map.put("arrivalTime",flight.getArrivalTime());
        map.put("totalEconomicSeats",flight.getEconomicClass().getTotalSeats());
        map.put("availableEconomicSeats",flight.getEconomicClass().getAvailableSeats());
        map.put("economicBasePrice",flight.getEconomicClass().getBasePrice());
        map.put("totalFirstClassSeats",flight.getFirstClass().getTotalSeats());
        map.put("availableFirstClassSeats",flight.getFirstClass().getAvailableSeats());
        map.put("firstClassBasePrice",flight.getFirstClass().getBasePrice());
        map.put("totalSecondClassSeats",flight.getSecondClass().getTotalSeats());
        map.put("availableSecondClassSeats",flight.getSecondClass().getAvailableSeats());
        map.put("secondClassBasePrice",flight.getSecondClass().getBasePrice());
        return map;
    }
}
