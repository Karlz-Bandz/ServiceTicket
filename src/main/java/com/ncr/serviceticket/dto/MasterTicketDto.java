package com.ncr.serviceticket.dto;

import lombok.Builder;

@Builder
public record MasterTicketDto (long atmId, String email, String clientDescription, String operatorDescription) {
}
