package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AddForumMessageDto;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.model.ForumMessage;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.ForumMessageRepository;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.service.impl.ForumMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForumServiceTest {

    @Mock
    private ForumMessageRepository chatMessageRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private ForumMessageServiceImpl chatService;

    private final Date currentDate = new Date();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    void getAllChatMessagesTest_SUCCESS() {
        ForumMessage chatMessage3 = ForumMessage.builder()
                .message("TestMessage3")
                .build();
        ForumMessage chatMessage4 = ForumMessage.builder()
                .message("TestMessage4")
                .build();
        ForumMessage chatMessage5 = ForumMessage.builder()
                .message("TestMessage5")
                .build();
        ForumMessage chatMessage6 = ForumMessage.builder()
                .message("TestMessage6")
                .build();
        ForumMessage chatMessage7 = ForumMessage.builder()
                .message("TestMessage7")
                .build();
        ForumMessage chatMessage8 = ForumMessage.builder()
                .message("TestMessage8")
                .build();
        ForumMessage chatMessage9 = ForumMessage.builder()
                .message("TestMessage9")
                .build();
        ForumMessage chatMessage10 = ForumMessage.builder()
                .message("TestMessage10")
                .build();
        ForumMessage chatMessage11 = ForumMessage.builder()
                .message("TestMessage11")
                .build();
        ForumMessage chatMessage12 = ForumMessage.builder()
                .message("TestMessage12")
                .build();

        List<ForumMessage> returnedMessages = Arrays.asList(
                chatMessage12,
                chatMessage11,
                chatMessage10,
                chatMessage9,
                chatMessage8,
                chatMessage7,
                chatMessage6,
                chatMessage5,
                chatMessage4,
                chatMessage3);

        when(chatMessageRepository.findAll()).thenReturn(returnedMessages);

        List<ForumMessage> result = chatService.getAllForumMessages();

        assertEquals(result, returnedMessages);
    }

    @Test
    void removeChatMessageTest_SUCCESS() {
        final AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        List<MessagePattern> messages = new ArrayList<>();
        List<ForumMessage> chatMessages = new ArrayList<>();

        Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .messages(messages)
                .forumMessages(chatMessages)
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        final ForumMessage chatMessage = ForumMessage.builder()
                .message("TestChat1")
                .date(simpleDateFormat.format(currentDate))
                .forumOperator(mockOperator)
                .build();

        when(chatMessageRepository.findById(1L)).thenReturn(Optional.ofNullable(chatMessage));

        chatService.deleteForumMessageById(1L);

        assert chatMessage != null;
        verify(chatMessageRepository).delete(chatMessage);
    }

    @Test
    void addChatMessageTest_SUCCESS() {
        final AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        List<MessagePattern> messages = new ArrayList<>();
        List<ForumMessage> chatMessages = new ArrayList<>();

        final Operator mockOperator = Operator.builder()
                .name("Test")
                .role("TestRole")
                .phone("555666444")
                .roles(Collections.singletonList(role))
                .messages(messages)
                .forumMessages(chatMessages)
                .email("karol@ss.pl")
                .password("xxxx")
                .build();

        final AddForumMessageDto addChatMessageDto = AddForumMessageDto.builder()
                .message("TestChat1")
                .email("karol@ss.pl")
                .build();

        final ForumMessage chatMessage = ForumMessage.builder()
                .message(addChatMessageDto.getMessage())
                .forumOperator(mockOperator)
                .date(simpleDateFormat.format(currentDate))
                .build();

        when(operatorRepository.findByEmail(addChatMessageDto.getEmail()))
                .thenReturn(Optional.ofNullable(mockOperator));

        when(chatMessageRepository.save(chatMessage)).thenReturn(chatMessage);

        chatService.addForumMessage(addChatMessageDto);

        verify(chatMessageRepository).save(chatMessage);
    }
}
