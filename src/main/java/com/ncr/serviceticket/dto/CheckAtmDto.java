package com.ncr.serviceticket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckAtmDto {
    private long id;
    private String atmId;
}
