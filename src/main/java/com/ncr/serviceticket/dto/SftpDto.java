package com.ncr.serviceticket.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SftpDto(
        long atmId,

        long sftpAtmId,

        @NotNull
        @Size(max = 100)
        String hostName,

        @NotNull
        @Size(max = 100)
        String userName,

        @NotNull
        int port,

        @NotNull
        @Size(max = 100)
        String pass) {
}
