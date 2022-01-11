package com.everest.model;

public class SeatType {
    private int totalSeats;
    private int availableSeats;
    private int basePrice;

    public SeatType() {}

    public SeatType(int totalSeats,int availableSeats,int basePrice){
        this.totalSeats=totalSeats;
        this.availableSeats=availableSeats;
        this.basePrice=basePrice;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setAvailableSeats(int availableSeats){
        this.availableSeats=availableSeats;
    }

    public void updateAvailableSeats(int noOfPassengersPerBooking) {
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
