package com.ncr.serviceticket.controller.impl;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.controller.SftpController;
import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.model.SftpAtm;
import com.ncr.serviceticket.service.SftpAtmService;
import com.ncr.serviceticket.service.SftpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SftpControllerImpl implements SftpController {

    private final SftpService sftpService;

    private final SftpAtmService sftpAtmService;

    @Override
    public ResponseEntity<SftpAtm> getSftpAtmById(long id) {
        return new ResponseEntity<>(sftpAtmService.getSftpAtmById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSftpAtmById(long id) {
        sftpAtmService.deleteSftpAtmById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> changeSftpAtm(SftpDto sftpDto) {
        sftpAtmService.changeSftpAtm(sftpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> addSftpAtm(SftpDto sftpDto) {
        sftpAtmService.addSftpAtm(sftpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> restart(String system) throws SftpException {
        sftpService.restart(system);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> disconnect() {
        sftpService.disconnect();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> connect(SftpDto ftpDto) throws JSchException {
        sftpService.connect(ftpDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
