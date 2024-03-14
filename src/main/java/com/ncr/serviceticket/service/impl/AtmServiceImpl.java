package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.dto.CheckAtmDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.service.AtmService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;

    public AtmServiceImpl(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Override
    public boolean existsBySerialNo(String serialNo) {
        return atmRepository.existsBySerialNo(serialNo);
    }

    @Override
    public boolean existsByAtmId(String atmId) {
        return atmRepository.existsByAtmId(atmId);
    }

    @Override
    @Transactional
    public void deleteAtmById(long id) {
        if(atmRepository.existsById(id)){
            atmRepository.deleteById(id);
        }else{
            throw new AtmNotFoundException("Atm does not found!");
        }
    }

    @Override
    @Transactional
    public void addNewAtm(AtmDto atmDto) {

        Atm atm = Atm.builder()
                .atmId(atmDto.getAtmId())
                .clientName(atmDto.getClientName())
                .serialNo(atmDto.getSerialNo())
                .type(atmDto.getType())
                .phone(atmDto.getPhone())
                .location(atmDto.getLocation())
                .build();

        if (atmRepository.existsByAtmId(atmDto.getAtmId())) {
            throw new AtmDuplicationException("AtmId already exists!");
        } else if (atmRepository.existsBySerialNo(atmDto.getSerialNo())) {
            throw new AtmDuplicationException("Serial No. already exists!");
        } else {
            atmRepository.save(atm);
        }
    }

    @Override
    public Atm findAtmById(long id) {
        return atmRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Atm was not found!")
        );
    }

    @Override
    public List<CheckAtmDto> getCheckList() {
        return atmRepository.getAtmCheckList()
                .stream()
                .sorted(Comparator.comparing(CheckAtmDto::getAtmId))
                .toList();
    }
}
