package com.ncr.serviceticket.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.dto.FtpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/ftp")
public interface FtpController {

    @PostMapping("/restart/{system}")
    ResponseEntity<Void> restart(@PathVariable("system") String system) throws SftpException, IOException;

    @PostMapping("/diconnect")
    ResponseEntity<Void> disconnect();

    @PostMapping("/connect")
    ResponseEntity<Void> connect(@RequestBody FtpDto ftpDto) throws JSchException, IOException;
}
