package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.model.Atm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/atm")
public interface AtmController {

    @GetMapping("/find/sn/{serialNo}")
    ResponseEntity<Atm> findBySerialNo(@PathVariable("serialNo") String serialNo);

    @GetMapping("/find/name/{atmId}")
    ResponseEntity<Atm> findByAtmId(@PathVariable("atmId") String atmId);

    @GetMapping("/exists/sn/{serialNo}")
    ResponseEntity<Boolean> existsBySerialNo(@PathVariable("serialNo") String serialNo);

    @GetMapping("/exists/{atmId}")
    ResponseEntity<Boolean> existsByAtmId(@PathVariable("atmId") String atmId);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteAtmById(@PathVariable("id") long id);

    @PostMapping("/add")
    ResponseEntity<Void> addNewAtm(@RequestBody @Valid AtmDto atmDto);

    @GetMapping("/find/{id}")
    ResponseEntity<Atm> findAtmById(@PathVariable("id") long id);

    @GetMapping("/checklist")
    ResponseEntity<List<CheckAtmDto>> getCheckList();
}
