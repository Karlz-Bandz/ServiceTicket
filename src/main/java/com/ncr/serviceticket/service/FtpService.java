package com.ncr.serviceticket.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.dto.FtpDto;

import java.io.IOException;

public interface FtpService {

    void restart(String system) throws IOException, SftpException;

    void connect(FtpDto ftpDto) throws IOException, JSchException;

    void disconnect();
}
