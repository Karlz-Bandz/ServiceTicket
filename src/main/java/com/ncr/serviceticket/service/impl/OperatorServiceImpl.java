package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.model.Role;
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

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerOperator(OperatorDto operatorDto) {
        Role roles = roleRepository.findByRole("USER").orElseThrow(() -> new RuntimeException("USER role not found!"));

        if (operatorRepository.existsByName(operatorDto.getName())) {
            throw new AtmDuplicationException("UserName already exists!");
        } else if (operatorRepository.existsByEmail(operatorDto.getEmail())) {
            throw new AtmDuplicationException("Email already exists!");
        } else {
            Operator operator = Operator.builder()
                    .name(operatorDto.getName())
                    .role(operatorDto.getRole())
                    .phone(operatorDto.getPhone())
                    .email(operatorDto.getEmail())
                    .password(passwordEncoder.encode(operatorDto.getPassword()))
                    .roles(Collections.singletonList(roles))
                    .build();

            operatorRepository.save(operator);
        }
        return "Operator added!";
    }

    @Override
    public String registerAdmin(OperatorDto operatorDto) {
        Role roles = roleRepository.findByRole("ADMIN").orElseThrow(() -> new RuntimeException("ADMIN role not found!"));

        if (operatorRepository.existsByName(operatorDto.getName())) {
            throw new AtmDuplicationException("UserName already exists!");
        } else if (operatorRepository.existsByEmail(operatorDto.getEmail())) {
            throw new AtmDuplicationException("Email already exists!");
        } else {
            Operator operator = Operator.builder()
                    .name(operatorDto.getName())
                    .role(operatorDto.getRole())
                    .phone(operatorDto.getPhone())
                    .email(operatorDto.getEmail())
                    .password(passwordEncoder.encode(operatorDto.getPassword()))
                    .roles(Collections.singletonList(roles))
                    .build();

            operatorRepository.save(operator);
        }
        return "Admin added!";
    }

    @Override
    public boolean operatorExistsByName(String name) {
        return operatorRepository.existsByName(name);
    }

    @Override
    @Transactional
    public void deleteOperatorById(long id) {
        if (operatorRepository.existsById(id)) {
            operatorRepository.deleteById(id);
        } else {
            throw new AtmNotFoundException("Operator is not found!");
        }
    }

    @Override
    public List<CheckOperatorDto> getCheckList() {
        return operatorRepository.getOperatorCheckList()
                .stream()
                .sorted(Comparator.comparing(CheckOperatorDto::getName))
                .toList();
    }

    @Override
    public Operator findById(long id) {
        return operatorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Operator was not found!")
        );
    }
}
