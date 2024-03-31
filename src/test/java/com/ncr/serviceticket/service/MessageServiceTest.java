package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.CheckMessageDto;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.MessagePatternRepository;
import com.ncr.serviceticket.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessagePatternRepository messagePatternRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void findEachIdByEmailTest_SUCCESS() {
        List<Long> mockIdList = Arrays.asList(1L, 3L, 4L);

        final String mockEmail = "john@ss.com";

        when(messagePatternRepository.findEachIdByEmail(mockEmail)).thenReturn(mockIdList);

        List<Long> result = messageService.findEachIdByEmail(mockEmail);

        assertEquals(result, mockIdList);
    }

    @Test
    void changeMessageByIdTest_SUCCESS() {
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

        MessagePattern messagePattern1 = MessagePattern.builder()
                .id(1L)
                .title("Title test")
                .message("Message test")
                .operator(mockOperator)
                .build();

        when(messagePatternRepository.findById(1L)).thenReturn(Optional.ofNullable(messagePattern1));

        MessagePattern myNewMessage = MessagePattern.builder()
                .title("Title new")
                .message("new Message")
                .id(1L)
                .operator(mockOperator)
                .build();

        when(messagePatternRepository.save(myNewMessage)).thenReturn(myNewMessage);

        MessagePattern argumentForTest = MessagePattern.builder()
                .id(1L)
                .title("Title new")
                .message("new Message")
                .build();

        messageService.changeMessageById(argumentForTest);

        verify(messagePatternRepository).save(myNewMessage);
    }

    @Test
    void getAllMessagesByEmailTest_SUCCESS() {
        final String userEmail = "john@ss.com";

        CheckMessageDto checkMessageDto1 = CheckMessageDto.builder()
                .id(1L)
                .message("Message1")
                .title("Title1")
                .build();
        CheckMessageDto checkMessageDto2 = CheckMessageDto.builder()
                .id(2L)
                .message("Message2")
                .title("Title2")
                .build();
        CheckMessageDto checkMessageDto3 = CheckMessageDto.builder()
                .id(3L)
                .message("Message3")
                .title("Title3")
                .build();

        List<CheckMessageDto> mockCheckMessages = Arrays.asList(
                checkMessageDto1,
                checkMessageDto2,
                checkMessageDto3);

        when(messagePatternRepository.findByOwnerUsername(userEmail)).thenReturn(mockCheckMessages);

        List<CheckMessageDto> result = messageService.getAllMessagesByEmail(userEmail);

        assertEquals(result, mockCheckMessages);
    }

    @Test
    void removeMessageTest_SUCCESS() {
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

        final MessagePattern messagePattern1 = MessagePattern.builder()
                .title("Title test")
                .message("Message test")
                .operator(mockOperator)
                .build();

        when(messagePatternRepository.findById(1L)).thenReturn(Optional.ofNullable(messagePattern1));

        messageService.deleteMessage(1L);

        assert messagePattern1 != null;
        verify(messagePatternRepository).delete(messagePattern1);
    }

    @Test
    void addMessageTest_SUCCESS() {
        final AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        List<MessagePattern> messages = new ArrayList<>();

        final Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .messages(messages)
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        final MessagePattern messagePattern = MessagePattern.builder()
                .title("Title")
                .message("Message")
                .operator(mockOperator)
                .build();

        when(messagePatternRepository.save(messagePattern)).thenReturn(messagePattern);

        messageService.addMessage(messagePattern);

        verify(messagePatternRepository).save(messagePattern);
    }
}
