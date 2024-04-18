package com.ncr.serviceticket.dto;

import lombok.Builder;

@Builder
public record AuthenticationDto(String email, String password, String token) {
}
