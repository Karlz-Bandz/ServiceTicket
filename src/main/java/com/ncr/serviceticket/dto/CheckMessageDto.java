package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckMessageDto {
    private long id;
    private String title;
    private String message;
}
