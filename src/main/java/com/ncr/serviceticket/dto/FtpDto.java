package com.ncr.serviceticket.dto;

import lombok.Builder;

@Builder
public record FtpDto(String hostName, String userName, int port, String pass) {
}
