package com.ncr.serviceticket.service;

import com.ncr.serviceticket.model.MessagePattern;

public interface MessageService {

    void addMessage(MessagePattern messagePattern);

    void deleteMessage(long id);
}
