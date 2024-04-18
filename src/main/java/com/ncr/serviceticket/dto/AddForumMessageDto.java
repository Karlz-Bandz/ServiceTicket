package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddForumMessageDto {
    private String email;
    private String message;
}
