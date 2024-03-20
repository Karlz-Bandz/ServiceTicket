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
    public ResponseEntity<String> registerUser(OperatorDto operatorDto) {
        return new ResponseEntity<>(operatorService.registerOperator(operatorDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> registerAdmin(OperatorDto operatorDto) {
        return new ResponseEntity<>(operatorService.registerAdmin(operatorDto), HttpStatus.CREATED);
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

//    @Override
//    public ResponseEntity<Void> addOperator(OperatorDto operatorDto) {
//        operatorService.addNewOperator(operatorDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @Override
    public ResponseEntity<Operator> findOperatorById(long id) {
        return ResponseEntity.ok(operatorService.findById(id));
    }

    @Override
    public ResponseEntity<List<CheckOperatorDto>> getOperatorCheckList() {
        return ResponseEntity.ok(operatorService.getCheckList());
    }
}
