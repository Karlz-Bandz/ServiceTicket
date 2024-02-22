package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtmDto {

    private long id;
    private String atmId;
    private String serialNo;
    private String type;
    private String clientName;
    private String location;
    private String phone;
}
