package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.OperatorController;
import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.service.OperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperatorControllerImpl implements OperatorController {

    private final OperatorService operatorService;

    public OperatorControllerImpl(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @Override
    public ResponseEntity<Void> addOperator(OperatorDto operatorDto) {
        operatorService.addNewOperator(operatorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Operator> findOperatorById(long id) {
        return ResponseEntity.ok(operatorService.findById(id));
    }

    @Override
    public ResponseEntity<List<CheckOperatorDto>> getOperatorCheckList() {
        return ResponseEntity.ok(operatorService.getCheckList());
    }
}
