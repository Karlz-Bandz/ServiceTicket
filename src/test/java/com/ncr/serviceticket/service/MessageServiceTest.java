package com.ncr.serviceticket.service;

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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessagePatternRepository messagePatternRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

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
