package com.ncr.serviceticket.exception.atm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtmErrorResponse {
    private int status;
    private String message;
}
