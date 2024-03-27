package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
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
    @Transactional
    public void addMessage(AddMessageDto addMessageDto) {
        Operator operator = operatorRepository.findById(addMessageDto.getId())
                .orElseThrow(() -> new AtmNotFoundException("User not found!"));

        operator.getMessages().add(addMessageDto.getMessagePattern());
        operatorRepository.save(operator);
    }

    @Override
    @Transactional
    public void removeMessage(RemoveMessageDto removeMessageDto) {
        Operator operator = operatorRepository.findById(removeMessageDto.getId())
                .orElseThrow(() -> new AtmNotFoundException("User not found!"));

        final int numberOfMessagesBefore = operator.getMessages().size();

        operator.getMessages().removeIf(message -> message.getId() == removeMessageDto.getMessageId());

        final int numberOfMessagesAfter = operator.getMessages().size();

        if(numberOfMessagesAfter == numberOfMessagesBefore){
            throw new AtmNotFoundException("Message doesn't exist!");
        }else {
            operatorRepository.save(operator);
        }
    }

    @Override
    public Optional<Operator> findByEmail(String email) {
        return operatorRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void registerOperator(OperatorDto operatorDto) {
        AuthorizationPosition roles = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("USER role not found!"));

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
    }

    @Override
    @Transactional
    public void registerAdmin(OperatorDto operatorDto) {
        AuthorizationPosition roles = roleRepository.findByRole("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found!"));

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
    public List<CheckOperatorDto> getCheckList() {
        return operatorRepository.getOperatorCheckList()
                .stream()
                .sorted(Comparator.comparing(CheckOperatorDto::getName))
                .toList();
    }
}
