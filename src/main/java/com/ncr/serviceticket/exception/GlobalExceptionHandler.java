package com.ncr.serviceticket.exception;

import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AtmDuplicationException.class)
    public ResponseEntity<AtmErrorResponse> handleGitUnauthorizedException(AtmDuplicationException ex) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        AtmErrorResponse gitErrorResponse = AtmErrorResponse.builder()
                .status(httpStatus.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(gitErrorResponse, httpStatus);
    }
}
