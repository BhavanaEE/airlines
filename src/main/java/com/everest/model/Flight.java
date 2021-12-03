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
    private int totalSeats;
    private int seatsOccupied;

    public Flight(long number, String source, String destination,LocalDate departureDate,LocalTime departureTime,LocalTime arrivalTime,int totalSeats,int seatsOccupied) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.totalSeats=totalSeats;
        this.seatsOccupied=seatsOccupied;
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

    public LocalTime getArrivalTime() { return arrivalTime;}

    public int getSeatsAvailable() { return totalSeats-seatsOccupied;}
}
