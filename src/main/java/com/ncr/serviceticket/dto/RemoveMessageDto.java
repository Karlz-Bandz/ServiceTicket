package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoveMessageDto {
    private long id;
    private long messageId;
}
