package com.ncr.serviceticket.service;

import com.ncr.serviceticket.model.Atm;

public interface AtmService {

    void addNewAtm(Atm atmDto);

    Atm findAtmById(long id);
}
