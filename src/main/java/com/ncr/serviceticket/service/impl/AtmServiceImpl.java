package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.service.AtmService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;

    public AtmServiceImpl(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Override
    public void addNewAtm(Atm atm) {
        try {
            atmRepository.save(atm);
        } catch (DataIntegrityViolationException err) {
            throw new AtmDuplicationException(err.getMessage());
        }
    }

    @Override
    public Atm findAtmById(long id) {
        return atmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Atm was not found!")
        );
    }
}
