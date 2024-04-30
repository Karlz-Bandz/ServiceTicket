package com.ncr.serviceticket.controller.impl;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.controller.FtpController;
import com.ncr.serviceticket.dto.FtpDto;
import com.ncr.serviceticket.service.FtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FtpControllerImpl implements FtpController {

    private final FtpService ftpService;

    @Override
    public ResponseEntity<Void> restart(String system) throws SftpException, IOException {
        ftpService.restart(system);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> disconnect() {
        ftpService.disconnect();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> connect(FtpDto ftpDto) throws JSchException, IOException {
        ftpService.connect(ftpDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
