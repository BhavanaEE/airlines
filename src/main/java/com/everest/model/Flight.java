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
    private int totalSeats=500;
    SeatType economicClass;
    SeatType firstClass;
    SeatType secondClass;
    private double totalFare;

    public Flight() {}

    public Flight(long number, String source, String destination, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime){
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate=departureDate;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setEconomicClass(SeatType economicClass) {
        this.economicClass = economicClass;
    }

    public void setFirstClass(SeatType firstClass) {
        this.firstClass = firstClass;
    }

    public void setSecondClass(SeatType secondClass) {
        this.secondClass = secondClass;
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

    public SeatType getSeatType(String seatType){
        switch (seatType) {
            case "EconomicClass":
                return this.economicClass;
            case "FirstClass":
                return this.firstClass;
            case "SecondClass":
                return this.secondClass;
        }
        return null;
    }

    private void setTotalSeats(int noOfPassengersForBooking) { this.totalSeats =noOfPassengersForBooking;}

    public int getTotalSeats() { return totalSeats;}

    public int getAvailableSeatsBySeatType(String flightType) {
        switch (flightType) {
            case "EconomicClass":
                return this.economicClass.getAvailableSeats();
            case "FirstClass":
                System.out.println("XXXX");
                return this.firstClass.getAvailableSeats();
            case "SecondClass":
                return this.secondClass.getAvailableSeats();

        }
        return 0;
    }

    public int getBasePriceBySeatType(String flightType) {
        switch (flightType) {
            case "EconomicClass":
                return economicClass.getBasePrice();
            case "FirstClass":
                return firstClass.getBasePrice();
            case "SecondClass":
                return secondClass.getBasePrice();

        }
        return 0;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public void setSeats(String[] flightDetail) {
        economicClass=new SeatType(Integer.parseInt(flightDetail[7]),Integer.parseInt(flightDetail[10]),Integer.parseInt(flightDetail[13]));
        firstClass=new SeatType(Integer.parseInt(flightDetail[8]),Integer.parseInt(flightDetail[11]),Integer.parseInt(flightDetail[14]));
        secondClass=new SeatType(Integer.parseInt(flightDetail[9]),Integer.parseInt(flightDetail[12]),Integer.parseInt(flightDetail[15]));
    }

    public String getFlightDetails(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(this.number+",");
        stringBuilder.append(this.source+",");
        stringBuilder.append(this.destination+",");
        stringBuilder.append(this.departureDate+",");
        stringBuilder.append(this.departureTime+",");
        stringBuilder.append(this.arrivalTime+",");
        stringBuilder.append(this.totalSeats+",");
        stringBuilder.append(economicClass.getTotalSeats()+",");
        stringBuilder.append(firstClass.getTotalSeats()+",");
        stringBuilder.append(secondClass.getTotalSeats()+",");
        stringBuilder.append(economicClass.getAvailableSeats()+",");
        stringBuilder.append(firstClass.getAvailableSeats()+",");
        stringBuilder.append(secondClass.getAvailableSeats()+",");
        stringBuilder.append(economicClass.getBasePrice()+",");
        stringBuilder.append(firstClass.getBasePrice()+",");
        stringBuilder.append(secondClass.getBasePrice());
        return stringBuilder.toString();
    }
}
