package com.everest.model;

import com.everest.service.PriceStrategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Flight {

    private long number;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int totalSeats;
    private int availableEconomicSeats;
    private int availableFirstClassSeats;
    private int availableSecondClassSeats;
    private int firstClassBasePrice;
    private int secondClassBasePrice;
    private int economicBasePrice;
    private int totalEconomicSeats;
    private int totalFirstClassSeats;
    private int totalSecondClassSeats;
    private double totalFare;

    public Flight(long number, String source, String destination, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime,int totalSeats,int totalEconomicSeats,int totalFirstClassSeats,int totalSecondClassSeats,int availableEconomicSeats, int availableFirstClassSeats, int availableSecondClassSeats, int economicBasePrice, int firstClassBasePrice, int secondClassBasePrice) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.totalSeats =totalSeats;
        this.totalEconomicSeats=totalEconomicSeats;
        this.totalFirstClassSeats=totalFirstClassSeats;
        this.totalSecondClassSeats=totalSecondClassSeats;
        this.availableEconomicSeats = availableEconomicSeats;
        this.availableFirstClassSeats = availableFirstClassSeats;
        this.availableSecondClassSeats = availableSecondClassSeats;
        this.economicBasePrice =economicBasePrice;
        this.firstClassBasePrice = firstClassBasePrice;
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

    public int getTotalSeats() { return totalSeats;}

    public int getTotalEconomicSeats() { return totalEconomicSeats;}

    public int getTotalFirstClassSeats() { return totalFirstClassSeats;}

    public int getTotalSecondClassSeats() { return totalSecondClassSeats;}

    public int getAvailableEconomicSeats() { return availableEconomicSeats;}

    public int getAvailableFirstClassSeats(){ return availableFirstClassSeats;}

    public int getAvailableSecondClassSeats(){ return availableSecondClassSeats;}

    public int getEconomicBasePrice(){ return economicBasePrice;}

    public int getFirstClassBasePrice(){ return firstClassBasePrice;}

    public int getSecondClassBasePrice(){ return secondClassBasePrice;}

    public void setSeatsAvailable(int noOfPassengersForBooking) { this.totalSeats -=noOfPassengersForBooking;}

    public void setAvailableEconomicSeats(int noOfPassengersForBooking){
        this.availableEconomicSeats -=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
    }

    public void setAvailableFirstClassSeats(int noOfPassengersForBooking){
        this.availableFirstClassSeats -=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
    }

    public void setAvailableSecondClassSeats(int noOfPassengersForBooking){
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

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }
}
