package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.AtmController;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.service.AtmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmControllerImpl implements AtmController {

    private final AtmService atmService;

    public AtmControllerImpl(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public ResponseEntity<Void> addNewAtm(Atm atm) {

        atmService.addNewAtm(atm);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Atm> findAtmById(long id) {
        return ResponseEntity.ok(atmService.findAtmById(id));
    }
}
