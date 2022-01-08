package com.everest.model;

public class SeatType {
    private int totalSeats;
    private int availableSeats;
    private int basePrice;
    public SeatType(int totalSeats,int availableSeats,int basePrice){
        this.totalSeats=totalSeats;
        this.availableSeats=availableSeats;
        this.basePrice=basePrice;
    }

    public void setAvailableSeats(int noOfPassengersPerBooking) {
        this.availableSeats -= noOfPassengersPerBooking;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getBasePrice() {
        return basePrice;
    }

}
