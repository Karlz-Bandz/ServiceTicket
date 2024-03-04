package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.model.Operator;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OperatorRepositoryTest {

    @Autowired
    private OperatorRepository operatorRepository;

    @Test
    void getOperatorCheckListTest() {
        List<CheckOperatorDto> result = operatorRepository.getOperatorCheckList();
        assertEquals(2, result.size());
        assertEquals("Iza Test", result.get(1).getName());
    }

    @BeforeAll
    @Transactional
    static void setData(@Autowired OperatorRepository operatorRepository) {

        Operator operator1 = Operator.builder()
                .name("Karol Test")
                .role("Operator")
                .phone("555666777")
                .build();
        Operator operator2 = Operator.builder()
                .name("Iza Test")
                .role("Operator")
                .phone("888666777")
                .build();

        operatorRepository.save(operator1);
        operatorRepository.save(operator2);
    }
}
