package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.model.Operator;

import java.util.List;

public interface OperatorService {

    boolean operatorExistsByName(String name);

    void deleteOperatorById(long id);

    List<CheckOperatorDto> getCheckList();

    Operator findById(long id);

    void addNewOperator(OperatorDto operatorDto);
}
