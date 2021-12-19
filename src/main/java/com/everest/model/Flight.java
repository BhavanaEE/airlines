package com.everest.model;

import com.everest.service.SearchFlightService;
import com.everest.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    @Autowired
    private SeatService seatService;

    private long number;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private final int totalSeats=500;
    private int availableSeats;
    private int availableEconomicSeats;
    private int availableFirstClassSeats;
    private int availableSecondClassSeats;
    private int firstClassBasePrice;
    private int secondClassBasePrice;
    private int economicBasePrice;
    private double totalFare;

    public Flight(long number, String source, String destination, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, int availableSeats, int availableEconomicSeats, int availableFirstClassSeats, int availableSecondClassSeats, int economicBasePrice, int firstClassBasePrice, int secondClassBasePrice) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.availableSeats =availableSeats;
        this.availableEconomicSeats = availableEconomicSeats;
        this.economicBasePrice =economicBasePrice;
        this.availableFirstClassSeats = availableFirstClassSeats;
        this.firstClassBasePrice = firstClassBasePrice;
        this.availableSecondClassSeats = availableSecondClassSeats;
        this.secondClassBasePrice = secondClassBasePrice;
    }

    public long getNumber() {
        return number;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() { return departureDate;}

    public LocalTime getDepartureTime() { return departureTime;}

    public LocalTime getArrivalTime() { return arrivalTime;}

    public int getAvailableSeats() { return availableSeats;}

    public int getAvailableEconomicSeats() { return availableEconomicSeats;}

    public int getAvailableFirstClassSeats(){ return availableFirstClassSeats;}

    public int getSecondClassBasePrice(){ return secondClassBasePrice;}

    public int getEconomicBasePrice(){ return economicBasePrice;}

    public int getFirstClassBasePrice(){ return firstClassBasePrice;}

    public int getAvailableSecondClassSeats(){ return availableSecondClassSeats;}

    public void setAvailableEconomicSeats(int noOfPassengersForBooking,int totalEconomicSeats){
        setTotalFare(getPrice(totalEconomicSeats,getAvailableEconomicSeats(),getEconomicBasePrice()));
        this.availableEconomicSeats -=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
    }

    public void setAvailableFirstClassSeats(int noOfPassengersForBooking,int totalFirstClassSeats){
        setTotalFare(getPrice(totalFirstClassSeats,availableFirstClassSeats,firstClassBasePrice));
        this.availableFirstClassSeats -=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
    }

    public void setAvailableSecondClassSeats(int noOfPassengersForBooking,int totalSecondClassSeats){
        setTotalFare(getPrice(totalSecondClassSeats,availableSecondClassSeats,secondClassBasePrice));
        this.availableSecondClassSeats -=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
    }

    public int getAvailableSeatsBySeatType(String flightType) {
        switch (flightType) {
            case "Economic Class":
                return availableEconomicSeats;
            case "First Class":
                return availableFirstClassSeats;
            case "Second Class":
                return availableSecondClassSeats;

        }
        return 0;
    }

    public int getBasePriceBySeatType(String flightType) {
        switch (flightType) {
            case "Economic Class":
                return economicBasePrice;
            case "First Class":
                return firstClassBasePrice;
            case "Second Class":
                return secondClassBasePrice;

        }
        return 0;
    }

    public double getPrice(int totalSeats,int availableSeats,int basePrice) {
        int booking=totalSeats-availableSeats;
        double multiplicationFactor=getMultiplicationFactor(totalSeats,booking);
        totalFare= basePrice +(basePrice *multiplicationFactor);
        return totalFare;
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

    public void setTotalFare(double price){this.totalFare=price;}

    public double getTotalFare(){
        return this.totalFare;}

    public void setSeatsAvailable(int noOfPassengersForBooking) {
        this.availableSeats-=noOfPassengersForBooking;
    }
}
