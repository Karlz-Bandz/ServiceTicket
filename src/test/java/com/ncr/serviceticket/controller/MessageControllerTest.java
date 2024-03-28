package com.ncr.serviceticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.OperatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(@Autowired OperatorService operatorService, @Autowired RoleRepository roleRepository) {

        AuthorizationPosition roleAdmin = AuthorizationPosition.builder()
                .role("ROLE_ADMIN")
                .build();

        AuthorizationPosition roleUser = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        MessagePattern messagePattern = MessagePattern.builder()
                .message("Test message")
                .title("Test title")
                .build();

        AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(2L)
                .messagePattern(messagePattern)
                .build();

        OperatorDto operatorDto1 = OperatorDto.builder()
                .name("TestOperator1")
                .phone("555666333")
                .role("Operator")
                .email("karol@ss.pl")
                .password("pass")
                .build();
        OperatorDto operatorDto2 = OperatorDto.builder()
                .name("TestOperator2")
                .phone("555666333")
                .role("Operator")
                .email("test@ss.pl")
                .password("pass")
                .build();

        operatorService.registerOperator(operatorDto1);
        operatorService.registerAdmin(operatorDto2);
        operatorService.addMessage(addMessageDto);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void removeMessage_SUCCESS() throws Exception {
        RemoveMessageDto removeMessageDto = RemoveMessageDto.builder()
                .id(2L)
                .messageId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/message/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(removeMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void addMessage_SUCCESS() throws Exception {
        MessagePattern messagePattern = MessagePattern.builder()
                .message("Test message")
                .title("Test title")
                .build();

        AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(1L)
                .messagePattern(messagePattern)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/message/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
