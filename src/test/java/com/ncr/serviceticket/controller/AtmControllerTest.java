package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.controller.impl.AtmControllerImpl;
import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.service.AtmService;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AtmControllerTest {

    @Mock
    private AtmService atmService;

    @InjectMocks
    private AtmControllerImpl atmController;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp(@Autowired AtmService atmService) {
        AtmDto atmDto1 = AtmDto.builder()
                .atmId("BPSA2211")
                .clientName("BankBankBank")
                .serialNo("13-333444333")
                .type("BANKOMAT")
                .phone("333444333")
                .location("Test Street 2343")
                .build();
        AtmDto atmDto2 = AtmDto.builder()
                .atmId("BPSA2291")
                .clientName("BankBankBank")
                .serialNo("13-373444333")
                .type("BANKOMAT")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        atmService.addNewAtm(atmDto1);
        atmService.addNewAtm(atmDto2);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_ADMIN")
    void deleteAtmByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/atm/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        AtmDto atmDto = AtmDto.builder()
                .atmId("BPSA2291")
                .clientName("BankBankBank")
                .serialNo("13-373444333")
                .type("BANKOMAT")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        atmService.addNewAtm(atmDto);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void existsByAtmIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/exists/BPSA2291")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void existsBySerialNoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/exists/sn/13-373444333")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void findByAtmIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/find/name/BPSA2211")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void findBySerialNoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/find/sn/13-333444333")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void getCheckListTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/checklist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void findByIdTest_SUCCESS() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/atm/find/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_ADMIN")
    void addNewAtmTest_SUCCESS() throws Exception {
        AtmDto atmDto = AtmDto.builder()
                .atmId("BPSA2811")
                .clientName("BankBankBank")
                .serialNo("73-333444333")
                .type("BANKOMAT")
                .phone("333444333")
                .location("Test Street 2343")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/atm/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atmDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
