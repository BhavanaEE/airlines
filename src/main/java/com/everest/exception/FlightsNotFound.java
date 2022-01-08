package com.everest.exception;

public class FlightsNotFound extends Exception{
    public FlightsNotFound(){
        super("No flight found");
    }
}
