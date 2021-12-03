package com.everest.model;

public class Flight {
    private long number;
    private String source;
    private String destination;

    public Flight(long number, String source, String destination) {
        this.number = number;
        this.source = source;
        this.destination = destination;
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

}
