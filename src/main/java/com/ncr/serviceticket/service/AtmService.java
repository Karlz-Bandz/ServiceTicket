package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.model.Atm;

public interface AtmService {

    void addNewAtm(AtmDto atmDto);

    Atm findAtmById(long id);
}
