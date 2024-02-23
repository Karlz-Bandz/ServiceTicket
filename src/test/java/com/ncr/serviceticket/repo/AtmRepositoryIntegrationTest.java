package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.model.Atm;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AtmRepositoryIntegrationTest {

    @Autowired
    private AtmRepository atmRepository;

    @Test
    void existsBySerialNoTest_SUCCESS() {
        boolean result = atmRepository.existsBySerialNo("13-3444333");
        assertTrue(result);
    }

    @Test
    void existsBySerialNoTest_NOT_FOUND() {
        boolean result = atmRepository.existsBySerialNo("13-31234333");
        assertFalse(result);
    }

    @Test
    void existsByAtmIdTest_SUCCESS() {
        boolean result = atmRepository.existsByAtmId("BPSA2233");
        assertTrue(result);
    }

    @Test
    void existsByAtmIdTest_NOT_FOUND() {
        boolean result = atmRepository.existsByAtmId("BPSA2218");
        assertFalse(result);
    }

    @Test
    void getAtmCheckListTest() {
        List<CheckAtmDto> result = atmRepository.getAtmCheckList();
        assertEquals(2, result.size());
    }

    @BeforeAll
    @Transactional
    static void setData(@Autowired AtmRepository atmRepository) {
        Atm atm1 = Atm.builder()
                .atmId("BPSA2233")
                .type("Type")
                .phone("5555555")
                .serialNo("13-3444333")
                .clientName("Bank w Warszawie")
                .location("Warsaw, ul. Ulica 56")
                .build();
        Atm atm2 = Atm.builder()
                .atmId("BPSA2211")
                .type("Type2")
                .phone("55553555")
                .serialNo("76-334433322")
                .clientName("Bank w Łodzi")
                .location("Łódź, ul. Test 56")
                .build();

        atmRepository.save(atm1);
        atmRepository.save(atm2);
    }
}
