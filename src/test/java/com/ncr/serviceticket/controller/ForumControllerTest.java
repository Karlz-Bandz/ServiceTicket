package com.ncr.serviceticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.serviceticket.dto.ForumMessageDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.ForumMessageService;
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
class ForumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(@Autowired OperatorService operatorService, @Autowired ForumMessageService forumMessageService, @Autowired RoleRepository roleRepository) {
        AuthorizationPosition roleAdmin = AuthorizationPosition.builder()
                .role("ROLE_ADMIN")
                .build();

        AuthorizationPosition roleUser = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

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

        final ForumMessageDto addForumMessageDto = ForumMessageDto.builder()
                .message("Testchat1")
                .email("test@ss.com")
                .build();

        forumMessageService.addForumMessage(addForumMessageDto);
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void getAllForumMessagesTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/forum/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "test2@ss.com", authorities = "ROLE_USER")
    void deleteForumMessageByIdTest_FORBIDDEN() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/forum/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void deleteForumMessageByIdTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/forum/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void addMessageTest_FORBBIDEN() throws Exception {
        final ForumMessageDto addForumMessageDto = ForumMessageDto.builder()
                .message("Testchat1")
                .email("test2@ss.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/forum/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addForumMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test@ss.com", authorities = "ROLE_USER")
    void addMessageTest_SUCCESS() throws Exception {
        final ForumMessageDto addForumMessageDto = ForumMessageDto.builder()
                .message("Testchat1")
                .email("test@ss.com")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/forum/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addForumMessageDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
