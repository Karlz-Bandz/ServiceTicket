package com.ncr.serviceticket.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.dto.SftpDto;

public interface SftpService {

    void restart(String system) throws SftpException;

    void connect(SftpDto ftpDto) throws JSchException;

    void disconnect();
}
