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

    @PostMapping("/register/user")
    ResponseEntity<String> registerUser(@RequestBody @Valid OperatorDto operatorDto);

    @PostMapping("/register/admin")
    ResponseEntity<String> registerAdmin(@RequestBody @Valid OperatorDto operatorDto);

    @GetMapping("/exists/{name}")
    ResponseEntity<Boolean> existsByName(@PathVariable("name") String name);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteOperatorById(@PathVariable("id") long id);

//    @PostMapping("/add")
//    ResponseEntity<Void> addOperator(@RequestBody @Valid OperatorDto operatorDto);

    @GetMapping("/find/{id}")
    ResponseEntity<Operator> findOperatorById(@PathVariable("id") long id);

    @GetMapping("/checklist")
    ResponseEntity<List<CheckOperatorDto>> getOperatorCheckList();
}
