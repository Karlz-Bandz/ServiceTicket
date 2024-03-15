package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.service.OperatorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
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

    @Override
    @Transactional
    public void addNewOperator(OperatorDto operatorDto) {

        if (operatorRepository.existsByName(operatorDto.getName())) {
            throw new AtmDuplicationException("UserName already exists!");
        } else {
            Operator operator = Operator.builder()
                    .name(operatorDto.getName())
                    .role(operatorDto.getRole())
                    .phone(operatorDto.getPhone())
                    .build();

            operatorRepository.save(operator);
        }
    }
}
