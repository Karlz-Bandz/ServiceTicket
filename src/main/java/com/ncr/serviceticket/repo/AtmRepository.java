package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.model.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {

    boolean existsByAtmId(String atmId);

    boolean existsBySerialNo(String serialNo);
}
