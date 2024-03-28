package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.impl.OperatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperatorServiceTest {

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    void removeMessageTest_SUCCESS() {
        final MessagePattern messagePattern1 = MessagePattern.builder()
                .id(1L)
                .title("Title test")
                .message("Message test")
                .build();

        final MessagePattern messagePattern2 = MessagePattern.builder()
                .id(2L)
                .title("Title test2")
                .message("Message test2")
                .build();

        final AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        List<MessagePattern> messages = new ArrayList<>(Arrays.asList(messagePattern1, messagePattern2));

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .messages(messages)
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        when(operatorRepository.findById(1L)).thenReturn(Optional.ofNullable(mockOperator));

        RemoveMessageDto removeMessageDto = RemoveMessageDto.builder()
                .id(1L)
                .messageId(1L)
                .build();

        operatorService.removeMessage(removeMessageDto);

        assert mockOperator != null;
        verify(operatorRepository).save(mockOperator);

        assertFalse(mockOperator.getMessages().contains(messagePattern1));
    }

    @Test
    void addMessageTest_OPERATOR_NOT_FOUND() {
        final MessagePattern messagePattern = MessagePattern.builder()
                .id(1L)
                .title("Title test")
                .message("Message test")
                .build();

        final AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(1L)
                .messagePattern(messagePattern)
                .build();

        when(operatorRepository.findById(1L)).thenThrow(AtmNotFoundException.class);

        assertThrows(AtmNotFoundException.class, () -> {
            operatorService.addMessage(addMessageDto);
        });
    }

    @Test
    void addMessageTest_SUCCESS() {
        final MessagePattern messagePattern = MessagePattern.builder()
                .id(1L)
                .title("Title test")
                .message("Message test")
                .build();

        final AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(1L)
                .messagePattern(messagePattern)
                .build();

        final AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        List<MessagePattern> messages = new ArrayList<>();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .messages(messages)
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        when(operatorRepository.findById(1L)).thenReturn(Optional.ofNullable(mockOperator));

        operatorService.addMessage(addMessageDto);

        assert mockOperator != null;
        verify(operatorRepository).save(mockOperator);

        assertTrue(mockOperator.getMessages().contains(messagePattern));
    }

    @Test
    void findByEmailTest_SUCCESS() {
        final String mockEmail = "john@kk.com";

        AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        when(operatorRepository.findByEmail(mockEmail))
                .thenReturn(Optional.ofNullable(mockOperator));

        Operator result = operatorService.findByEmail(mockEmail)
                .orElseThrow(() -> new AtmNotFoundException("Operator not found!"));

        assertEquals(result, mockOperator);
    }

    @Test
    void operatorExistsByEmailTest_SUCCESS() {
        final String mockEmail = "john@kk.com";

        when(operatorRepository.existsByEmail(mockEmail))
                .thenReturn(true);

        boolean result = operatorService.operatorExistsByEmail(mockEmail);

        assertTrue(result);
    }

    @Test
    void operatorExistsByNameTest_SUCCESS() {
        final String mockName = "John";

        when(operatorRepository.existsByName(mockName))
                .thenReturn(true);

        boolean result = operatorService.operatorExistsByName(mockName);

        assertTrue(result);
    }

    @Test
    void deleteOperatorByIdTest_OPERATOR_NOT_FOUND() {
        when(operatorRepository.findById(1L)).thenThrow(AtmNotFoundException.class);

        assertThrows(AtmNotFoundException.class, () -> {
            operatorService.deleteOperatorById(1L);
        });
    }

    @Test
    void deleteOperatorByIdTest_SUCCESS() {
        AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        when(operatorRepository.findById(1L)).thenReturn(Optional.ofNullable(mockOperator));

        operatorService.deleteOperatorById(1L);

        verify(operatorRepository).delete(mockOperator);
    }

    @Test
    void registerUserTest_SUCCESS() {
        AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        OperatorDto operatorDto = OperatorDto.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .password("pass")
                .email("karol@ss.pl")
                .build();

        when(roleRepository.findByRole(role.getRole())).thenReturn(Optional.of(role));

        when(passwordEncoder.encode(operatorDto.getPassword())).thenReturn("xxxx");

        when(operatorRepository.save(mockOperator)).thenReturn(mockOperator);

        operatorService.registerOperator(operatorDto);

        verify(operatorRepository).save(mockOperator);
    }

    @Test
    void registerAdminTest_SUCCESS() {
        AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_ADMIN")
                .build();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        OperatorDto operatorDto = OperatorDto.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .password("pass")
                .email("karol@ss.pl")
                .build();

        when(roleRepository.findByRole(role.getRole())).thenReturn(Optional.of(role));

        when(passwordEncoder.encode(operatorDto.getPassword())).thenReturn("xxxx");

        when(operatorRepository.save(mockOperator)).thenReturn(mockOperator);

        operatorService.registerAdmin(operatorDto);

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
}
