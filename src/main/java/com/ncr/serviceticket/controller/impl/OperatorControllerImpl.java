package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.OperatorController;
import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperatorControllerImpl implements OperatorController {

    private final OperatorService operatorService;

    @Override
    public ResponseEntity<Void> registerUser(OperatorDto operatorDto) {
        operatorService.registerOperator(operatorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> registerAdmin(OperatorDto operatorDto) {
        operatorService.registerAdmin(operatorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> existsByName(String name) {
        return ResponseEntity.ok(operatorService.operatorExistsByName(name));
    }

    @Override
    public ResponseEntity<Void> deleteOperatorById(long id) {
        operatorService.deleteOperatorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Override
    public ResponseEntity<List<CheckOperatorDto>> getOperatorCheckList() {
        return ResponseEntity.ok(operatorService.getCheckList());
    }
}
