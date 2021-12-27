package com.everest.service;

import com.everest.model.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class PriceStrategy {
    private double totalFareBySeat;
    private double totalFareByDate;

    public void calculateFareForEachFlight(List<Flight> flightList, String flightType) {
        flightList.stream().forEach(flight -> filterSeatOnSeatType(flight,flightType));
    }

    public double filterSeatOnSeatType(Flight flight, String classType){
        switch (classType){
            case "Economic Class":
                setTotalFareBySeat(getPrice(flight.getTotalEconomicSeats(),flight.getAvailableEconomicSeats(),flight.getEconomicBasePrice()));
                setTotalFareByDate(getPriceByDate(flight.getDepartureDate(),todayDate(),flight.getEconomicBasePrice()));
                System.out.println("bhavana "+getTotalFare());
                return getTotalFare();
            case "First Class":
                setTotalFareBySeat(getPrice(flight.getTotalFirstClassSeats(),flight.getAvailableFirstClassSeats(),flight.getFirstClassBasePrice()));
                setTotalFareByDate(getPriceByDate(flight.getDepartureDate(),todayDate(),flight.getFirstClassBasePrice()));
                return getTotalFare();
            case "Second Class":
                setTotalFareBySeat(getPrice(flight.getTotalSecondClassSeats(),flight.getAvailableSecondClassSeats(),flight.getSecondClassBasePrice()));
                setTotalFareByDate(getPriceByDate(flight.getDepartureDate(),todayDate(),flight.getSecondClassBasePrice()));
                return getTotalFare();
        }
        return 0;
    }

    public double getPrice(int totalSeats,int availableSeats,int basePrice) {
        int booking=totalSeats-availableSeats;
        double multiplicationFactor=getMultiplicationFactor(totalSeats,booking);
        this.totalFareBySeat = basePrice +(basePrice *multiplicationFactor);
        return this.totalFareBySeat;
    }

    public double getPriceByDate(LocalDate date1, LocalDate date2, int basePrice) {
        long diffDays = ChronoUnit.DAYS.between(date1, date2);
        int days=Math.abs((int)diffDays);
        double multiplicationFactorByDate=getMultiplicationFactorByDate(days);
        totalFareByDate=(basePrice*multiplicationFactorByDate);
        return totalFareByDate;
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
            return 0*booking;
        if(booking>3 && booking<=10)
            return 0.02*booking;
        if(booking<=3)
            return 0.1*booking;
        return 0;
    }

    public void setTotalFareBySeat(double price){this.totalFareBySeat =price;}

    public void setTotalFareByDate(double price){ this.totalFareByDate=price;}

    public double getTotalFare(){ return this.totalFareBySeat+this.totalFareByDate;}

    public LocalDate todayDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date=formatter.format(now);
        return LocalDate.parse(date, formatter);
    }
}
