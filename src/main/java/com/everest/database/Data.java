package com.everest.database;

import com.everest.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Data {
    public static List<Flight> flights = List.of(
            new Flight(1001, "Hyderabad", "Bangalore", getDate(2022,1,01), getTime(5,30),getTime(6,54),30),
            new Flight(1002, "Bangalore", "Hyderabad",getDate(2022,1,01),getTime(1,20),getTime(9,56),32),
            new Flight(1003, "Goa", "Bangalore",getDate(2021,12,21),getTime(12,12),getTime(3,23),75),
            new Flight(1004, "Bangalore", "Goa",getDate(2021,12,11),getTime(3,50),getTime(7,54),51),
            new Flight(1005, "Bangalore", "Hyderabad",getDate(2022,1,01),getTime(5,47),getTime(1,30),10),
            new Flight(1006, "Hyderabad", "Bangalore",getDate(2022,1,02),getTime(2,40),getTime(4,40),2));

    public static LocalTime getTime(int hours, int minutes){
        return LocalTime.of(hours,minutes);
    }
    public static LocalDate getDate(int year,int month,int dayOfMonth){
        return LocalDate.of(year,month,dayOfMonth);
    }
}