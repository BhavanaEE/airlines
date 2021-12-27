package com.everest.model;

import java.util.Comparator;

public class SortFlights implements Comparator<Flight> {
    @Override
    public int compare(Flight a, Flight b) {
        return (int) (a.getNumber() - b.getNumber());
    }
}
