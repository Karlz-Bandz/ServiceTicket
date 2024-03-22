package com.ncr.serviceticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.serviceticket.controller.impl.OperatorControllerImpl;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Role;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.OperatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class OperatorControllerTest {

    @Mock
    private OperatorService operatorService;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private OperatorControllerImpl operatorController;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp(@Autowired OperatorService operatorService, @Autowired RoleRepository roleRepository) {

        Role roleAdmin = Role.builder()
                .role("ROLE_ADMIN")
                .build();

        Role roleUser = Role.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

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
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_ADMIN")
    void addNewOperatorTest_SUCCESS() throws Exception {
        OperatorDto operatorDto = OperatorDto.builder()
                .name("Test1")
                .phone("443322")
                .role("Operator")
                .email("test2@ss.pl")
                .password("pass")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/operator/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operatorDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_ADMIN")
    void getCheckList_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/operator/checklist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
