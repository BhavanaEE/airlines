package com.everest.exception;

public class LimitExceed extends Exception{
    public LimitExceed(){
        super("Number of passengers is exceeding limit.Please enter required number.");
    }
}
