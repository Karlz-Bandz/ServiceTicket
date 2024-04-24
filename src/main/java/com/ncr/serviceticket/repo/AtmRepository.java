package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.AtmDto;
import com.ncr.serviceticket.model.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

    Atm findByAtmId(String atmId);

    Atm findBySerialNo(String serialNo);

    boolean existsByAtmId(String atmId);

    boolean existsBySerialNo(String serialNo);

    @Query("SELECT new com.ncr.serviceticket.dto.AtmDto(a.id, a.atmId) FROM Atm a")
    List<AtmDto> getAtmCheckList();
}
