package com.everest.controller;

import com.everest.FlightRowMapper;
import com.everest.dto.FlightDTO;
import com.everest.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightsApiController {
    @Autowired
    private FlightDTO flightDTO;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping("/flights")
    public List<Flight> getAllFlights(){
        return jdbcTemplate.query("SELECT * FROM flight_database.Flight f join flight_database.FlightDetail fd  on f.`Number` = fd.`Number`", new FlightRowMapper());
    }

    @GetMapping("/flight/{number}")
    public Flight getFlight(@PathVariable("number") long number){
        Map<String,Object> map=new HashMap<>();
        map.put("number",number);
        return jdbcTemplate.queryForObject("SELECT * FROM flight_database.Flight f join flight_database.FlightDetail fd  on f.`Number` =fd.`Number` and f.`Number` =:number", map, new FlightRowMapper());
    }

    @PostMapping("/flight")
    public long create(@RequestBody Flight flight){
        Map<String,Object> map=flightDTO.getFlightDetails(flight);
        jdbcTemplate.update("insert into flight_database.Flight(Number,Source,Destination,DepartureDate,DepartureTime,ArrivalTime) values(:number,:source,:destination,:departureDate,:departureTime,:arrivalTime)",map);
        jdbcTemplate.update("insert into flight_database.FlightDetail values(:totalEconomicSeats,:availableEconomicSeats,:economicBasePrice,:totalFirstClassSeats,:availableFirstClassSeats,:firstClassBasePrice,:totalSecondClassSeats,:availableSecondClassSeats,:secondClassBasePrice,:number)",map);
        return flight.getNumber();
    }

    @PutMapping("/flight/{number}")
    public Flight update(@PathVariable long number,@RequestBody Flight flight){
        Map<String,Object> map=flightDTO.getFlightDetails(flight);
        jdbcTemplate.update("update flight_database.Flight f set f.Number=:number,f.Source =:source,f.Destination=:destination,f.DepartureDate=:departureDate,f.DepartureTime=:departureTime,f.ArrivalTime=:arrivalTime where f.Number=:number",map);
        jdbcTemplate.update("UPDATE flight_database.FlightDetail fd set fd.TotalEconomicSeats=:totalEconomicSeats,fd.AvailableEconomicSeats=:availableEconomicSeats,fd.EconomicBasePrice=:economicBasePrice,fd.TotalFirstClassSeats=:totalFirstClassSeats,fd.AvailableFirstClassSeats=:availableFirstClassSeats,fd.FirstClassBasePrice=:firstClassBasePrice,fd.TotalSecondClassSeats=:totalSecondClassSeats,fd.AvailableSecondClassSeats=:availableSecondClassSeats,fd.SecondClassBasePrice=:secondClassBasePrice where fd.Number=:number",map);
        return jdbcTemplate.queryForObject("SELECT * FROM flight_database.Flight f join flight_database.FlightDetail fd  on f.`Number` =fd.`Number` and f.`Number` =:number", map, new FlightRowMapper());
    }

    @DeleteMapping("/flight/{number}")
    public int delete(@PathVariable("number") long number){
        Map<String ,Object> map=new HashMap<>();
        map.put("Number",number);
        return jdbcTemplate.update("delete from Flight where Number=:Number",map);
    }
}
