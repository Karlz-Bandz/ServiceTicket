package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.model.SftpAtm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.repo.SftpAtmRepository;
import com.ncr.serviceticket.service.SftpAtmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SftpAtmServiceImpl implements SftpAtmService {

    private final AtmRepository atmRepository;

    private final SftpAtmRepository sftpRepository;

    @Override
    public SftpAtm getSftpAtmById(long id) {
        return sftpRepository.findById(id)
                .orElseThrow(() -> new AtmNotFoundException("SftpAtm not found!"));
    }

    @Override
    public void deleteSftpAtmById(long id) {
        SftpAtm sftpAtm = sftpRepository.findById(id)
                .orElseThrow(() -> new AtmNotFoundException("SftpAtm not found!"));

        sftpRepository.delete(sftpAtm);
    }

    @Override
    public void changeSftpAtm(SftpDto sftpDto) {
        SftpAtm sftpAtm = sftpRepository.findById(sftpDto.sftpAtmId())
                .orElseThrow(() -> new AtmNotFoundException("SftpAtm not found!"));

        sftpAtm.setPort(sftpDto.port());
        sftpAtm.setPassword(sftpDto.pass());
        sftpAtm.setUserName(sftpDto.userName());
        sftpAtm.setHostName(sftpDto.hostName());

        sftpRepository.save(sftpAtm);
    }

    @Override
    public void addSftpAtm(SftpDto sftpDto) {
        final Atm atm = atmRepository.findById(sftpDto.atmId())
                .orElseThrow(() -> new AtmNotFoundException("Atm not found!"));

        final SftpAtm sftpAtm = SftpAtm.builder()
                .port(sftpDto.port())
                .hostName(sftpDto.hostName())
                .password(sftpDto.pass())
                .userName(sftpDto.userName())
                .atm(atm)
                .build();

        sftpRepository.save(sftpAtm);
    }
}
