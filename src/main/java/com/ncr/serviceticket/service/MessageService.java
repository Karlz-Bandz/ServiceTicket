package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.MessageDto;
import com.ncr.serviceticket.model.MessagePattern;

import java.util.List;

public interface MessageService {

    List<Long> findEachIdByEmail(String email);

    void changeMessageById(MessagePattern messagePattern);

    List<MessageDto> getAllMessagesByEmail(String email);

    void addMessage(MessagePattern messagePattern);

    void deleteMessage(long id);
}
