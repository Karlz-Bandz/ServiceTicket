package com.ncr.serviceticket.exception.atm;

public class AtmNotFoundException extends RuntimeException{
    public AtmNotFoundException(String message){
        super(message);
    }
}
