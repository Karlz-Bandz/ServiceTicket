package com.ncr.serviceticket.dto;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperatorDto {

    private long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String role;

    @NotBlank
    @Size(max = 50)
    @ContactNumberConstraint
    private String phone;
}
