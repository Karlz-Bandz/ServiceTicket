package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.service.impl.AtmServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtmServiceTest {

    @Mock
    private AtmRepository atmRepository;

    @InjectMocks
    private AtmServiceImpl atmService;

    @Test
    void addNewAtmTest_SERIALNO_ALREADY_EXISTS() {
        AtmDto atmDto = AtmDto.builder()
                .atmId("BPSA2211")
                .clientName("BankBankBank")
                .serialNo("13-333444333")
                .type("Banokmat")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        when(atmRepository.existsBySerialNo(atmDto.getSerialNo())).thenReturn(true);

        assertThrows(AtmDuplicationException.class, () -> {
            atmService.addNewAtm(atmDto);
        });
    }

    @Test
    void addNewAtmTest_ATMID_ALREADY_EXISTS() {
        AtmDto atmDto = AtmDto.builder()
                .atmId("BPSA2211")
                .clientName("BankBankBank")
                .serialNo("13-333444333")
                .type("Banokmat")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        when(atmRepository.existsByAtmId(atmDto.getAtmId())).thenReturn(true);

        assertThrows(AtmDuplicationException.class, () -> {
            atmService.addNewAtm(atmDto);
        });
    }

    @Test
    void addNewAtmTest_SUCCESS() {
        Atm atm = Atm.builder()
                .atmId("BPSA2211")
                .clientName("BankBankBank")
                .serialNo("13-333444333")
                .type("Banokmat")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        AtmDto atmDto = AtmDto.builder()
                .atmId("BPSA2211")
                .clientName("BankBankBank")
                .serialNo("13-333444333")
                .type("Banokmat")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        when(atmRepository.save(atm)).thenReturn(atm);

        atmService.addNewAtm(atmDto);

        verify(atmRepository).save(atm);
    }

    @Test
    void getCheckListTest() {
        CheckAtmDto checkAtmDto1 = CheckAtmDto.builder()
                .id(1)
                .atmId("BBAA2211")
                .build();
        CheckAtmDto checkAtmDto2 = CheckAtmDto.builder()
                .id(2)
                .atmId("BBDD2211")
                .build();
        List<CheckAtmDto> mockCheckList = List.of(checkAtmDto1, checkAtmDto2);

        when(atmRepository.getAtmCheckList()).thenReturn(mockCheckList);

        List<CheckAtmDto> result = atmService.getCheckList();

        assertEquals(mockCheckList, result);
    }

    @Test
    void findAtmByIdTest_Not_FOUND() {
        RuntimeException mockException = mock(RuntimeException.class);

        when(atmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            atmService.findAtmById(1L);
        });
    }

    @Test
    void findAtmByIdTest() {
        Atm mockAtm = Atm.builder()
                .atmId("BPSA2233")
                .type("Type")
                .phone("5555555")
                .serialNo("13-3444333")
                .clientName("Bank w Warszawie")
                .location("Warsaw, ul. Ulica 56")
                .build();

        when(atmRepository.findById(1L)).thenReturn(Optional.of(mockAtm));

        Atm result = atmService.findAtmById(1L);

        assertEquals(mockAtm, result);
    }
}
