package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.model.Atm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AtmRepositoryTest {

    @Autowired
    private AtmRepository atmRepository;

    @Test
    void getAtmCheckListTest_SUCCESS() {

        Atm atm1 = Atm.builder()
                .atmId("rrrrrkkkk")
                .type("Type")
                .phone("5555555")
                .serialNo("2223344443")
                .clientName("NAmename")
                .location("loc")
                .build();
        Atm atm2 = Atm.builder()
                .atmId("rrrrttkkkk")
                .type("Type2")
                .phone("55553555")
                .serialNo("22423344443")
                .clientName("NfAmename")
                .location("locd")
                .build();

        atmRepository.save(atm1);
        atmRepository.save(atm2);

        List<CheckAtmDto> result = atmRepository.getAtmCheckList();

        assertEquals(result.size(), 2);
    }
}
