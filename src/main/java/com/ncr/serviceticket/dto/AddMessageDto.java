package com.ncr.serviceticket.dto;

import com.ncr.serviceticket.model.MessagePattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddMessageDto {
    private long id;
    private MessagePattern messagePattern;
}
