package com.ncr.serviceticket.dto;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import com.ncr.serviceticket.validation.annotations.UpperCaseConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AtmDto(

        @NotNull
        long id,

        @NotBlank
        @Size(min = 8, max = 10)
        @UpperCaseConstraint
        String atmId,

        @NotBlank
        @Size(min = 8, max = 20)
        String serialNo,

        @NotBlank
        @Size(max = 20)
        @UpperCaseConstraint
        String type,

        @NotBlank
        @Size(max = 120)
        String clientName,

        @NotBlank
        @Size(max = 50)
        String location,

        @NotBlank
        @Size(max = 20)
        @ContactNumberConstraint
        String phone
) {
        public AtmDto(long id, String atmId){
                this(id, atmId, null, null, null, null, null);
        }
}
