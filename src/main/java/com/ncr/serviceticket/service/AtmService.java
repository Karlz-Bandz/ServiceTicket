package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.model.Atm;

import java.util.List;

public interface AtmService {

    boolean existsBySerialNo(String serialNo);

    boolean existsByAtmId(String atmId);

    void deleteAtmById(long id);

    void addNewAtm(AtmDto atmDto);

    Atm findAtmById(long id);

    List<CheckAtmDto> getCheckList();
}
