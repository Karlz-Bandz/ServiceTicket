package com.ncr.serviceticket.dto;

import lombok.Builder;

@Builder
public record ForumMessageDto(String email, String message) {
}
