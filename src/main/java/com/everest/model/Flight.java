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

    public Flight(long number, String source, String destination,LocalDate departureDate,LocalTime departureTime,LocalTime arrivalTime,int availableSeats) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.availableSeats =availableSeats;
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

    public void setSeatsAvailable(int noOfPassengersForBooking) {
        this.availableSeats=noOfPassengersForBooking;
    }

    public String getFlightDetails() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(getNumber());
        stringBuilder.append(getDestination());
        stringBuilder.append(getSource());
        stringBuilder.append(getDepartureDate());
        stringBuilder.append(getAvailableSeats());
        return stringBuilder.toString();
    }
}
