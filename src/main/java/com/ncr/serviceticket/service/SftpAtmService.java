package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.model.SftpAtm;

public interface SftpAtmService {

    SftpAtm getSftpAtmById(long id);

    void deleteSftpAtmById(long id);

    void changeSftpAtm(SftpDto sftpDto);

    void addSftpAtm(SftpDto sftpDto);
}
