package com.ncr.serviceticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.serviceticket.controller.impl.OperatorControllerImpl;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.service.OperatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class OperatorControllerTest {

    @Mock
    private OperatorService operatorService;

    @InjectMocks
    private OperatorControllerImpl operatorController;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp(@Autowired OperatorService operatorService) {

        OperatorDto operatorDto1 = OperatorDto.builder()
                .name("TestOperator1")
                .phone("555666333")
                .role("Operator")
                .build();
        OperatorDto operatorDto2 = OperatorDto.builder()
                .name("TestOperator2")
                .phone("555666333")
                .role("Operator")
                .build();

        operatorService.addNewOperator(operatorDto1);
        operatorService.addNewOperator(operatorDto2);
    }

    @Test
    void addNewAtmTest_SUCCESS() throws Exception {
        OperatorDto operatorDto = OperatorDto.builder()
                .name("Test1")
                .phone("443322")
                .role("Operator")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/operator/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operatorDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findByIdTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/operator/find/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCheckList_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/operator/checklist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
