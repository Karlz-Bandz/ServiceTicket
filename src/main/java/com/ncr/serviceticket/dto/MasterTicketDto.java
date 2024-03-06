package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterTicketDto {
    private long atmId;
    private long operatorId;
    private String clientDescription;
    private String operatorDescription;
}
