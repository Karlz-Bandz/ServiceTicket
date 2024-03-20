package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDto {
    private String email;
    private String password;
}
