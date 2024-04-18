package com.ncr.serviceticket.dto;

import lombok.Builder;

@Builder
public record MessageDto(long id, String email, String title, String message) {

    public MessageDto(long id, String title, String message) {
        this(id, null, title, message);
    }
}
