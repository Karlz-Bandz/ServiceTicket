package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.model.Atm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/atm")
public interface AtmController {

    @PostMapping("/add")
    ResponseEntity<Void> addNewAtm(@RequestBody @Valid Atm atm);

    @GetMapping("/find/{id}")
    ResponseEntity<Atm> findAtmById(@PathVariable("id") long id);
}
