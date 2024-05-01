package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.model.SftpAtm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SftpAtmRepository extends JpaRepository<SftpAtm, Long> {
}
