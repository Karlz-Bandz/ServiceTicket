package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.model.AuthorizationPosition;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OperatorRepositoryTest {

    @Autowired
    private OperatorRepository operatorRepository;

    @BeforeAll
    @Transactional
    static void setData(@Autowired RoleRepository roleRepository, @Autowired OperatorRepository operatorRepository) {

        AuthorizationPosition role = AuthorizationPosition.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(role);

        AuthorizationPosition roleForOperator = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("USER role not found!"));

        Operator operator1 = Operator.builder()
                .name("Karol Test")
                .role("Operator")
                .phone("555666777")
                .roles(Collections.singletonList(roleForOperator))
                .email("karol@ss.pl")
                .password("pass")
                .build();
        Operator operator2 = Operator.builder()
                .name("Iza Test")
                .role("Operator")
                .phone("888666777")
                .roles(Collections.singletonList(roleForOperator))
                .email("iza@ss.pl")
                .password("pass")
                .build();

        operatorRepository.save(operator1);
        operatorRepository.save(operator2);
    }

    @Test
    void getOperatorCheckListTest() {
        List<OperatorDto> result = operatorRepository.getOperatorCheckList();
        assertEquals(2, result.size());
        assertEquals("Iza Test", result.get(1).name());
    }
}
