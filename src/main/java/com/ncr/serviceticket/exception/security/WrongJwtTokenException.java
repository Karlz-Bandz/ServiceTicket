package com.ncr.serviceticket.exception.security;

public class WrongJwtTokenException extends RuntimeException{
    public WrongJwtTokenException(String message) {
        super(message);
    }
}
