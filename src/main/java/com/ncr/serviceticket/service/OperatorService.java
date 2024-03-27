package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.CheckOperatorDto;
import com.ncr.serviceticket.dto.OperatorDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import com.ncr.serviceticket.model.Operator;

import java.util.List;
import java.util.Optional;

public interface OperatorService {

    void addMessage(AddMessageDto addMessageDto);

    void removeMessage(RemoveMessageDto removeMessageDto);

    Optional<Operator> findByEmail(String email);

    void registerOperator(OperatorDto operatorDto);

    void registerAdmin(OperatorDto operatorDto);

    boolean operatorExistsByName(String name);

    boolean operatorExistsByEmail(String email);

    void deleteOperatorById(long id);

    List<CheckOperatorDto> getCheckList();
}
