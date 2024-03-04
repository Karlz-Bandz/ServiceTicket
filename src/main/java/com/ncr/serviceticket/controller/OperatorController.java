package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/operator")
public interface OperatorController {

    @PostMapping("/add")
    ResponseEntity<Void> addOperator(@RequestBody @Valid OperatorDto operatorDto);

    @GetMapping("/find/{id}")
    ResponseEntity<Operator> findOperatorById(@PathVariable("id") long id);

    @GetMapping("/checklist")
    ResponseEntity<List<CheckOperatorDto>> getOperatorCheckList();
}
