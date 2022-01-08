package com.everest.service;

import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class PriceStrategy {

    public void calculateFareForEachFlight(List<Flight> flightList, String flightType,int noOfPassengersPerBooking) {
        flightList.forEach(flight -> calculateTotalFare(flight,flightType,noOfPassengersPerBooking));
    }

    public void calculateTotalFare(Flight flight, String classType, int noOfPassengersPerBooking){
        double totalFareBySeat= getPriceBySeat(flight.getSeatType(classType).getTotalSeats(),flight.getSeatType(classType).getAvailableSeats(),flight.getSeatType(classType).getBasePrice());
        double totalFareByDate=getPriceByDate(flight.getDepartureDate(),todayDate(),flight.getSeatType(classType).getBasePrice());
        flight.setTotalFare((totalFareBySeat+totalFareByDate)*noOfPassengersPerBooking);
    }

    public double getPriceBySeat(int totalSeats, int availableSeats, int basePrice) {
        int bookedSeats=totalSeats-availableSeats;
        double multiplicationFactor=getMultiplicationFactor(totalSeats,bookedSeats);
        return basePrice +(basePrice *multiplicationFactor);
    }

    public double getPriceByDate(LocalDate date1, LocalDate date2, int basePrice) {
        long diffDays = ChronoUnit.DAYS.between(date1, date2);
        int days=Math.abs((int)diffDays);
        double multiplicationFactorByDate=getMultiplicationFactorByDate(days);
        return basePrice*multiplicationFactorByDate;
    }

    public double getMultiplicationFactor(int totalSeats,int booking){
        if(booking<Math.floor(totalSeats*0.3))
            return 0;
        if(booking<Math.floor(totalSeats*0.5))
            return 0.2;
        if(booking<Math.floor(totalSeats*0.75))
            return 0.35;
        return 0.5;
    }

    public double getMultiplicationFactorByDate(int booking){
        if(booking>10 && booking<=15)
            return 0;
        if(booking>3 && booking<=10)
            return 0.02*booking;
        if(booking<=3)
            return 0.1*booking;
        return 0;
    }

    public LocalDate todayDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date=formatter.format(now);
        return LocalDate.parse(date, formatter);
    }
}
