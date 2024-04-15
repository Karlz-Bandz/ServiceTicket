package com.ncr.serviceticket.exception;

import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.exception.security.WrongJwtTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongJwtTokenException.class)
    public ResponseEntity<CustomErrorResponse> handleWrongJwtTokenException(WrongJwtTokenException ex) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        CustomErrorResponse gitErrorResponse = CustomErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }

    @ExceptionHandler(AtmDuplicationException.class)
    public ResponseEntity<CustomErrorResponse> handleAtmDuplicationException(AtmDuplicationException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        CustomErrorResponse gitErrorResponse = CustomErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }

    @ExceptionHandler(AtmNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleAtmNotFoundException(AtmNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomErrorResponse gitErrorResponse = CustomErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }
}
