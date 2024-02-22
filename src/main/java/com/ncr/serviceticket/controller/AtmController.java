package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.model.Atm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/atm")
public interface AtmController {

    @PostMapping("/add")
    ResponseEntity<Void> addNewAtm(@RequestBody AtmDto atmDto);

    @GetMapping("/find/{id}")
    ResponseEntity<Atm> findAtmById(@PathVariable("id") long id);
}
