package com.everest.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private long number;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int availableSeats;
    private int economicSeats;
    private int firstClassSeats;
    private int secondClassSeats;
    private int firstClassPrice;
    private int secondClassPrice;
    private int economicPrice;
    private int totalFare;

    public Flight(long number, String source, String destination, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, int availableSeats, int economicSeats, int firstClassSeats, int secondClassSeats,int economicPrice,int firstClassPrice,int secondClassPrice) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.availableSeats =availableSeats;
        this.economicSeats=economicSeats;
        this.economicPrice=economicPrice;
        this.firstClassSeats = firstClassSeats;
        this.firstClassPrice=firstClassPrice;
        this.secondClassSeats = secondClassSeats;
        this.secondClassPrice=secondClassPrice;
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

    public int getEconomicSeats() { return economicSeats;}

    public int getFirstClassSeats(){ return firstClassSeats;}

    public int getSecondClassPrice(){ return secondClassPrice;}

    public int getEconomicPrice(){ return economicPrice;}

    public int getFirstClassPrice(){ return firstClassPrice;}

    public int getSecondClassSeats(){ return secondClassSeats;}

    public void setEconomicSeats(int noOfPassengersForBooking){
        this.economicSeats-=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
        setTotalFare(getEconomicPrice(),noOfPassengersForBooking);
    }

    public void setFirstClassSeats(int noOfPassengersForBooking){
        this.firstClassSeats-=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
        setTotalFare(getFirstClassPrice(),noOfPassengersForBooking);
    }

    public void setSecondClassSeats(int noOfPassengersForBooking){
        this.secondClassSeats-=noOfPassengersForBooking;
        setSeatsAvailable(noOfPassengersForBooking);
        setTotalFare(getSecondClassPrice(),noOfPassengersForBooking);
    }

    public int getAvailableSeatsBySeatType(String flightType) {
        switch (flightType) {
            case "Economic Class":
                return economicSeats;
            case "First Class":
                return firstClassSeats;
            case "Second Class":
                return secondClassSeats;

        }
        return 0;
    }

    public int getBasePriceBySeatType(String flightType) {
        switch (flightType) {
            case "Economic Class":
                return economicPrice;
            case "First Class":
                return firstClassPrice;
            case "Second Class":
                return secondClassPrice;

        }
        return 0;
    }

    public void setTotalFare(int price,int noOfPassengersForBooking){this.totalFare=price*noOfPassengersForBooking;}

    public int getTotalFare(){ return this.totalFare;}

    public void setSeatsAvailable(int noOfPassengersForBooking) {
        this.availableSeats-=noOfPassengersForBooking;
    }
}
