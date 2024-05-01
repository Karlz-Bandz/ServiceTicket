package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.model.SftpAtm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.repo.SftpAtmRepository;
import com.ncr.serviceticket.service.impl.SftpAtmServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SftpAtmServiceTest {

    @Mock
    private AtmRepository atmRepository;

    @Mock
    private SftpAtmRepository sftpAtmRepository;

    @InjectMocks
    private SftpAtmServiceImpl sftpAtmService;

    @Test
    void getSftpAtmByIdTest_SUCCESS() {
        final int port = 22;
        final String host = "122.333.4444.55";
        final String userName = "user1234";
        final String password = "pass";

        final SftpAtm sftpAtm = SftpAtm.builder()
                .id(1L)
                .userName(userName)
                .port(port)
                .hostName(host)
                .password(password)
                .build();

        when(sftpAtmRepository.findById(1L)).thenReturn(Optional.ofNullable(sftpAtm));

        SftpAtm sftpResult = sftpAtmService.getSftpAtmById(1L);

        assertEquals(sftpResult, sftpAtm);
    }

    @Test
    void deleteSftpAtmByIdTest_SUCCESS() {
        final int port = 22;
        final String host = "122.333.4444.55";
        final String userName = "user1234";
        final String password = "pass";

        Atm mockAtm = Atm.builder()
                .id(1L)
                .atmId("BPSA1122")
                .phone("444333222")
                .location("Test location")
                .type("BANKOMAT")
                .serialNo("13-222333333")
                .clientName("Client test name")
                .build();

        final SftpAtm sftpAtm = SftpAtm.builder()
                .id(1L)
                .userName(userName)
                .port(port)
                .hostName(host)
                .password(password)
                .atm(mockAtm)
                .build();

        when(sftpAtmRepository.findById(1L)).thenReturn(Optional.ofNullable(sftpAtm));

        sftpAtmService.deleteSftpAtmById(1L);

        assert sftpAtm != null;
        verify(sftpAtmRepository).delete(sftpAtm);
    }

    @Test
    void changeSftpAtmTest_SUCCESS() {
        final int port = 22;
        final String host = "122.333.4444.55";
        final String userName = "user1234";
        final String password = "pass";

        final Atm mockAtm = Atm.builder()
                .id(1L)
                .atmId("BPSA1122")
                .phone("444333222")
                .location("Test location")
                .type("BANKOMAT")
                .serialNo("13-222333333")
                .clientName("Client test name")
                .build();

        final SftpAtm sftpAtm = SftpAtm.builder()
                .id(1L)
                .userName(userName)
                .port(port)
                .hostName(host)
                .password(password)
                .atm(mockAtm)
                .build();

        final SftpAtm newSftpAtm = SftpAtm.builder()
                .id(1L)
                .userName(userName)
                .port(44)
                .hostName(host)
                .password("newpass")
                .atm(mockAtm)
                .build();

        final SftpDto sftpDto = SftpDto.builder()
                .sftpAtmId(1L)
                .userName(userName)
                .port(44)
                .hostName(host)
                .pass("newpass")
                .build();


        when(sftpAtmRepository.findById(1L)).thenReturn(Optional.ofNullable(sftpAtm));
        when(sftpAtmRepository.save(newSftpAtm)).thenReturn(newSftpAtm);

        sftpAtmService.changeSftpAtm(sftpDto);

        verify(sftpAtmRepository).save(newSftpAtm);
    }

    @Test
    void addSftpAtmTest_SUCCESS() {
        final int port = 22;
        final String host = "122.333.4444.55";
        final String userName = "user1234";
        final String password = "pass";

        final Atm mockAtm = Atm.builder()
                .atmId("BPSA1122")
                .phone("444333222")
                .location("Test location")
                .type("BANKOMAT")
                .serialNo("13-222333333")
                .clientName("Client test name")
                .build();

        final SftpAtm sftpAtm = SftpAtm.builder()
                .userName(userName)
                .port(port)
                .hostName(host)
                .password(password)
                .atm(mockAtm)
                .build();

        final SftpDto sftpDto = SftpDto.builder()
                .atmId(1L)
                .port(port)
                .userName(userName)
                .pass(password)
                .hostName(host)
                .build();

        when(atmRepository.findById(1L)).thenReturn(Optional.ofNullable(mockAtm));
        when(sftpAtmRepository.save(sftpAtm)).thenReturn(sftpAtm);

        sftpAtmService.addSftpAtm(sftpDto);

        verify(sftpAtmRepository).save(sftpAtm);
    }
}
