package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;

import java.util.List;
import java.util.Optional;

public interface OperatorService {

    Optional<Operator> findByEmail(String email);

    void registerOperator(OperatorDto operatorDto);

    void registerAdmin(OperatorDto operatorDto);

    boolean operatorExistsByName(String name);

    void deleteOperatorById(long id);

    List<CheckOperatorDto> getCheckList();
}
