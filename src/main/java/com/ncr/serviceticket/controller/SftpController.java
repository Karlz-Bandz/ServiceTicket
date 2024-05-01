package com.ncr.serviceticket.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.model.SftpAtm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ftp")
public interface SftpController {

    @GetMapping("/find/{id}")
    ResponseEntity<SftpAtm> getSftpAtmById(@PathVariable("id") long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteSftpAtmById(@PathVariable("id") long id);

    @PutMapping("/change")
    ResponseEntity<Void> changeSftpAtm(@Valid @RequestBody SftpDto sftpDto);

    @PostMapping("/add")
    ResponseEntity<Void> addSftpAtm(@Valid @RequestBody SftpDto sftpDto);

    @PostMapping("/restart/{system}")
    ResponseEntity<Void> restart(@PathVariable("system") String system) throws SftpException;

    @PostMapping("/disconnect")
    ResponseEntity<Void> disconnect();

    @PostMapping("/connect")
    ResponseEntity<Void> connect(@RequestBody SftpDto ftpDto) throws JSchException;
}
