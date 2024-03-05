package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.service.impl.OperatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperatorServiceTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    void addNewAtmTest_SUCCESS() {
        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .build();

        OperatorDto operatorDto = OperatorDto.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .build();

        when(operatorRepository.save(mockOperator)).thenReturn(mockOperator);

        operatorService.addNewOperator(operatorDto);

        verify(operatorRepository).save(mockOperator);
    }

    @Test
    void getCheckListTest() {

        CheckOperatorDto checkOperatorDto1 = CheckOperatorDto.builder()
                .id(1L)
                .name("TestName1")
                .build();
        CheckOperatorDto checkOperatorDto2 = CheckOperatorDto.builder()
                .id(2L)
                .name("TestName2")
                .build();

        List<CheckOperatorDto> mockCheckList = List.of(checkOperatorDto1, checkOperatorDto2);

        when(operatorRepository.getOperatorCheckList()).thenReturn(mockCheckList);

        List<CheckOperatorDto> result = operatorService.getCheckList();

        assertEquals(mockCheckList, result);
    }

    @Test
    void findAtmByIdTest_NOT_FOUND() {
        when(operatorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            operatorService.findById(1L);
        });
    }

    @Test
    void findAtmByIdTest() {
        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .build();

        when(operatorRepository.findById(1L)).thenReturn(Optional.of(mockOperator));

        Operator result = operatorService.findById(1L);

        assertEquals(mockOperator, result);
    }
}
