package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.exception.atm.AtmDuplicationException;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.repo.AtmRepository;
import com.ncr.serviceticket.service.AtmService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;

    @Override
    @Transactional
    public Atm findBySerialNo(String serialNo) {
        if(atmRepository.existsBySerialNo(serialNo)){
            return atmRepository.findBySerialNo(serialNo);
        }else{
            throw new AtmNotFoundException("Serial no. doesn't exists!");
        }
    }

    @Override
    @Transactional
    public Atm findByAtmId(String atmId) {
        if(atmRepository.existsByAtmId(atmId)){
            return atmRepository.findByAtmId(atmId);
        }else {
            throw new AtmNotFoundException("Atm doesn't exist!");
        }
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
        if (atmRepository.existsById(id)) {
            atmRepository.deleteById(id);
        } else {
            throw new AtmNotFoundException("Atm does not found!");
        }
    }

    @Override
    @Transactional
    public void addNewAtm(AtmDto atmDto) {

        if (atmRepository.existsByAtmId(atmDto.atmId())) {
            throw new AtmDuplicationException("AtmId already exists!");
        } else if (atmRepository.existsBySerialNo(atmDto.serialNo())) {
            throw new AtmDuplicationException("Serial No. already exists!");
        } else {
            Atm atm = Atm.builder()
                    .atmId(atmDto.atmId())
                    .clientName(atmDto.clientName())
                    .serialNo(atmDto.serialNo())
                    .type(atmDto.type())
                    .phone(atmDto.phone())
                    .location(atmDto.location())
                    .build();

            atmRepository.save(atm);
        }
    }

    @Override
    public Atm findAtmById(long id) {
        return atmRepository.findById(id).orElseThrow(
                () -> new AtmNotFoundException("Atm does not found!")
        );
    }

    @Override
    public List<AtmDto> getCheckList() {
        return atmRepository.getAtmCheckList()
                .stream()
                .sorted(Comparator.comparing(AtmDto::atmId))
                .toList();
    }
}
