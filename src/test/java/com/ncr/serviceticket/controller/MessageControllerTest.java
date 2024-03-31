package com.ncr.serviceticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.MessageService;
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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(@Autowired OperatorService operatorService, @Autowired MessageService messageService, @Autowired RoleRepository roleRepository) {
        AuthorizationPosition roleAdmin = AuthorizationPosition.builder()
                .role("ROLE_ADMIN")
                .build();

        AuthorizationPosition roleUser = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        List<MessagePattern> messages = new ArrayList<>();

        AddMessageDto addMessageDto = AddMessageDto.builder()
                .email("user@ss.com")
                .message("test")
                .title("titleTest")
                .build();

        OperatorDto operatorDto1 = OperatorDto.builder()
                .name("TestOperator1")
                .phone("555666333")
                .role("Operator")
                .email("user@ss.com")
                .password("pass")
                .build();
        OperatorDto operatorDto2 = OperatorDto.builder()
                .name("TestOperator2")
                .phone("555666333")
                .role("Operator")
                .email("test@ss.com")
                .password("pass")
                .build();

        operatorService.registerOperator(operatorDto1);
        operatorService.registerAdmin(operatorDto2);

        Operator mockOperator = operatorService.findByEmail(operatorDto2.getEmail())
                .orElseThrow(() -> new AtmNotFoundException("User not found!"));

        MessagePattern messagePattern = MessagePattern.builder()
                .operator(mockOperator)
                .message("TESTTEST")
                .title("TITLE")
                .build();

        messageService.addMessage(messagePattern);
    }

    @Test
    @WithMockUser(username = "user@ss.com", authorities = "ROLE_USER")
    void changeMessageByIdTest_FORBIDDEN() throws Exception {
        AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(1L)
                .message("New")
                .title("newTitle")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/message/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void changeMessageByIdTest_SUCCESS() throws Exception {
        AddMessageDto addMessageDto = AddMessageDto.builder()
                .id(1L)
                .message("New")
                .title("newTitle")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/message/change")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void getAllMessagesTest_FORBIDDEN() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/message/test2@ss.com/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void getAllMessagesTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/message/test@ss.com/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "test2@ss.com", authorities = "ROLE_USER")
    void removeMessage_FORBIDDEN() throws Exception {
        RemoveMessageDto removeMessageDto = RemoveMessageDto.builder()
                .messageId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/message/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(removeMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void removeMessage_SUCCESS() throws Exception {
        RemoveMessageDto removeMessageDto = RemoveMessageDto.builder()
                .messageId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/message/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(removeMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user@ss.com", authorities = "ROLE_USER")
    void addMessage_FORBIDDEN() throws Exception {
        AddMessageDto addMessageDto = AddMessageDto.builder()
                .email("another@ss.com")
                .title("TitleTest")
                .message("MessageTest")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/message/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user@ss.com", authorities = "ROLE_USER")
    void addMessage_SUCCESS() throws Exception {
        AddMessageDto addMessageDto = AddMessageDto.builder()
                .email("user@ss.com")
                .title("TitleTest")
                .message("MessageTest")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/message/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
