package com.ncr.serviceticket.dto;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record OperatorDto (

        @NotNull
        long id,

        @NotBlank
        @Size(max = 50)
        String name,

        @NotBlank
        @Size(max = 50)
        String role,

        @NotBlank
        @Size(max = 50)
        @ContactNumberConstraint
        String phone,

        @NotBlank
        @Size(max = 32)
        String email,

        @NotBlank
        @Size(max = 200)
        String password
) {
  public OperatorDto(long id, String name){
      this(id, name, null, null, null, null);
  }
}
