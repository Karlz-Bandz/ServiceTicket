package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.service.OperatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public List<CheckOperatorDto> getCheckList() {
        return operatorRepository.getOperatorCheckList();
    }

    @Override
    public Operator findById(long id) {
        return operatorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Operator was not found!")
        );
    }

    @Override
    public void addNewOperator(OperatorDto operatorDto) {
        Operator operator = Operator.builder()
                .name(operatorDto.getName())
                .role(operatorDto.getRole())
                .phone(operatorDto.getPhone())
                .build();

        operatorRepository.save(operator);
    }
}
