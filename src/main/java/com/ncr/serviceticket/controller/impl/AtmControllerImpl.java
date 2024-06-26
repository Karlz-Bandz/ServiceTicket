package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.AtmController;
import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.service.AtmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AtmControllerImpl implements AtmController {

    private final AtmService atmService;

    @Override
    public ResponseEntity<Atm> findBySerialNo(String serialNo) {
        return ResponseEntity.ok(atmService.findBySerialNo(serialNo));
    }

    @Override
    public ResponseEntity<Atm> findByAtmId(String atmId) {
        return ResponseEntity.ok(atmService.findByAtmId(atmId));
    }

    @Override
    public ResponseEntity<Boolean> existsBySerialNo(String serialNo) {
        return ResponseEntity.ok(atmService.existsBySerialNo(serialNo));
    }

    @Override
    public ResponseEntity<Boolean> existsByAtmId(String atmId) {
        return ResponseEntity.ok(atmService.existsByAtmId(atmId));
    }

    @Override
    public ResponseEntity<Void> deleteAtmById(long id) {
        atmService.deleteAtmById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> addNewAtm(AtmDto atmDto) {
        atmService.addNewAtm(atmDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Atm> findAtmById(long id) {
        return ResponseEntity.ok(atmService.findAtmById(id));
    }

    @Override
    public ResponseEntity<List<CheckAtmDto>> getCheckList() {
        return ResponseEntity.ok(atmService.getCheckList());
    }
}
