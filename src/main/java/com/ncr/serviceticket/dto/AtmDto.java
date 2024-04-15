package com.ncr.serviceticket.dto;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import com.ncr.serviceticket.validation.annotations.UpperCaseConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtmDto {

    private long id;

    @NotBlank
    @Size(min = 8, max = 10)
    @UpperCaseConstraint
    private String atmId;

    @NotBlank
    @Size(min = 8, max = 20)
    private String serialNo;

    @NotBlank
    @Size(max = 20)
    @UpperCaseConstraint
    private String type;

    @NotBlank
    @Size(max = 120)
    private String clientName;

    @NotBlank
    @Size(max = 50)
    private String location;

    @NotBlank
    @Size(max = 20)
    @ContactNumberConstraint
    private String phone;
}
