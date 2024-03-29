package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddMessageDto {
    private long id;
    private String email;
    private String title;
    private String message;
}
