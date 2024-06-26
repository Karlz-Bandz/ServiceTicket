package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Optional<Operator> findById(long id);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<Operator> findByEmail(String email);

    @Query("SELECT new com.ncr.serviceticket.dto.CheckOperatorDto(a.id, a.name) FROM Operator a")
    List<CheckOperatorDto> getOperatorCheckList();
}
