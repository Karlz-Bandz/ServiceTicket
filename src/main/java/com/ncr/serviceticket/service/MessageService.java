package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.CheckMessageDto;
import com.ncr.serviceticket.model.MessagePattern;

import java.util.List;

public interface MessageService {

    List<CheckMessageDto> getAllMessagesByEmail(String email);

    void addMessage(MessagePattern messagePattern);

    void deleteMessage(long id);
}
