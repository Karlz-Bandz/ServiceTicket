package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtmServiceTest {

    @Mock
    private AtmRepository atmRepository;

    @InjectMocks
    private AtmServiceImpl atmService;

    @Test
    void existsAtmIdTest_SUCCESS() {

        final String atmId = "TESTIDID";

        when(atmRepository.existsByAtmId(atmId))
                .thenReturn(true);

        boolean result = atmService.existsByAtmId(atmId);

        assertTrue(result);
    }

    @Test
    void existsBySerialNoTest_SUCCESS() {

        final String mockSerial = "13-2222222";

        when(atmRepository.existsBySerialNo(mockSerial))
                .thenReturn(true);

        boolean result = atmService.existsBySerialNo(mockSerial);

        assertTrue(result);
    }

    @Test
    void findByAtmIdTest_NO_NOT_FOUND() {

        final String atmId = "TESTIDID";

        when(atmRepository.existsByAtmId(atmId))
                .thenReturn(false);

        assertThrows(AtmNotFoundException.class, () -> {
            atmService.findByAtmId(atmId);
        });
    }

    @Test
    void findByAtmIdTest_SUCCESS() {
        Atm mockAtm = Atm.builder()
                .atmId("BPSA2233")
                .type("Type")
                .phone("5555555")
                .serialNo("13-3444333")
                .clientName("Bank w Warszawie")
                .location("Warsaw, ul. Ulica 56")
                .build();

        when(atmRepository.existsByAtmId(mockAtm.getAtmId()))
                .thenReturn(true);

        when(atmRepository.findByAtmId(mockAtm.getAtmId()))
                .thenReturn(mockAtm);

        Atm result = atmService.findByAtmId(mockAtm.getAtmId());

        assertEquals(mockAtm, result);
    }

    @Test
    void findBySerialNOTest_NOT_FOUND() {

        final String mockSerial = "13-2222222";

        when(atmRepository.existsBySerialNo(mockSerial))
                .thenReturn(false);

        assertThrows(AtmNotFoundException.class, () -> {
            atmService.findBySerialNo(mockSerial);
        });
    }

    @Test
    void findBySerialNOTest_SUCCESS() {
        Atm mockAtm = Atm.builder()
                .atmId("BPSA2233")
                .type("Type")
                .phone("5555555")
                .serialNo("13-3444333")
                .clientName("Bank w Warszawie")
                .location("Warsaw, ul. Ulica 56")
                .build();

        when(atmRepository.existsBySerialNo(mockAtm.getSerialNo()))
                .thenReturn(true);

        when(atmRepository.findBySerialNo(mockAtm.getSerialNo()))
                .thenReturn(mockAtm);

        Atm result = atmService.findBySerialNo(mockAtm.getSerialNo());

        assertEquals(mockAtm, result);
    }

    @Test
    void deleteAtmByIdTest_ATM_NOT_FOUND() {
        when(atmRepository.existsById(1L)).thenReturn(false);

        assertThrows(AtmNotFoundException.class, () -> {
            atmService.deleteAtmById(1L);
        });
    }

    @Test
    void deleteAtmByIdTest_SUCCESS() {
        when(atmRepository.existsById(1L)).thenReturn(true);

        atmService.deleteAtmById(1L);

        verify(atmRepository).deleteById(1L);
    }

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

        when(atmRepository.existsBySerialNo(atmDto.serialNo())).thenReturn(true);

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

        when(atmRepository.existsByAtmId(atmDto.atmId())).thenReturn(true);

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
        AtmDto checkAtmDto1 = AtmDto.builder()
                .id(1)
                .atmId("BBAA2211")
                .build();
        AtmDto checkAtmDto2 = AtmDto.builder()
                .id(2)
                .atmId("BBDD2211")
                .build();
        List<AtmDto> mockCheckList = List.of(checkAtmDto1, checkAtmDto2);

        when(atmRepository.getAtmCheckList()).thenReturn(mockCheckList);

        List<AtmDto> result = atmService.getCheckList();

        assertEquals(mockCheckList, result);
    }

    @Test
    void findAtmByIdTest_Not_FOUND() {
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
