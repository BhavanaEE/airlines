package com.everest;

import com.everest.model.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightRowMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        Flight flight = new Flight(rs.getLong("Number"), rs.getString("Source"), rs.getString("Destination"), rs.getDate("DepartureDate").toLocalDate(), rs.getTime("DepartureTime").toLocalTime(), rs.getTime("ArrivalTime").toLocalTime());
        String[] flightDetails={rs.getString("TotalEconomicSeats"),rs.getString("AvailableEconomicSeats"),rs.getString("EconomicBasePrice"),rs.getString("TotalFirstClassSeats"),rs.getString("AvailableFirstClassSeats"),rs.getString("FirstClassBasePrice"),rs.getString("TotalSecondClassSeats"),rs.getString("AvailableSecondClassSeats"),rs.getString("SecondClassBasePrice")};
        flight.setSeats(flightDetails);
        return flight;
    }
}
