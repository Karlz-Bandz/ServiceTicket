package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.model.AuthorizationPosition;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.repo.RoleRepository;
import com.ncr.serviceticket.service.OperatorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Operator> findByEmail(String email) {
        return operatorRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void registerOperator(OperatorDto operatorDto) {
        AuthorizationPosition roles = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("USER role not found!"));

        if (operatorRepository.existsByName(operatorDto.name())) {
            throw new AtmDuplicationException("UserName already exists!");
        } else if (operatorRepository.existsByEmail(operatorDto.email())) {
            throw new AtmDuplicationException("Email already exists!");
        } else {
            Operator operator = Operator.builder()
                    .name(operatorDto.name())
                    .role(operatorDto.role())
                    .phone(operatorDto.phone())
                    .email(operatorDto.email())
                    .password(passwordEncoder.encode(operatorDto.password()))
                    .roles(Collections.singletonList(roles))
                    .build();

            operatorRepository.save(operator);
        }
    }

    @Override
    @Transactional
    public void registerAdmin(OperatorDto operatorDto) {
        AuthorizationPosition roles = roleRepository.findByRole("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found!"));

        if (operatorRepository.existsByName(operatorDto.name())) {
            throw new AtmDuplicationException("UserName already exists!");
        } else if (operatorRepository.existsByEmail(operatorDto.email())) {
            throw new AtmDuplicationException("Email already exists!");
        } else {
            Operator operator = Operator.builder()
                    .name(operatorDto.name())
                    .role(operatorDto.role())
                    .phone(operatorDto.phone())
                    .email(operatorDto.email())
                    .password(passwordEncoder.encode(operatorDto.password()))
                    .roles(Collections.singletonList(roles))
                    .build();

            operatorRepository.save(operator);
        }
    }

    @Override
    public boolean operatorExistsByName(String name) {
        return operatorRepository.existsByName(name);
    }

    @Override
    public boolean operatorExistsByEmail(String email) {
        return operatorRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void deleteOperatorById(long id) {
       Operator operator = operatorRepository.findById(id)
               .orElseThrow(() -> new AtmNotFoundException("Operator not found!"));

       operatorRepository.delete(operator);
    }

    @Override
    public List<OperatorDto> getCheckList() {
        return operatorRepository.getOperatorCheckList()
                .stream()
                .sorted(Comparator.comparing(OperatorDto::name))
                .toList();
    }
}
