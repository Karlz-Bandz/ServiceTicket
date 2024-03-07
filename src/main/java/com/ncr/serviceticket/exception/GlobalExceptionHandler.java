package com.ncr.serviceticket.exception;

import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmErrorResponse;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AtmDuplicationException.class)
    public ResponseEntity<AtmErrorResponse> handleAtmDuplicationException(AtmDuplicationException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        AtmErrorResponse gitErrorResponse = AtmErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }

    @ExceptionHandler(AtmNotFoundException.class)
    public ResponseEntity<AtmErrorResponse> handleAtmNotFoundException(AtmNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        AtmErrorResponse gitErrorResponse = AtmErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }
}
