package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.repo.MessagePatternRepository;
import com.ncr.serviceticket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessagePatternRepository messagePatternRepository;

    @Override
    public void addMessage(MessagePattern messagePattern) {
        messagePatternRepository.save(messagePattern);
    }

    @Override
    public void deleteMessage(long id) {
        MessagePattern messagePattern = messagePatternRepository.findById(id)
                        .orElseThrow(() -> new AtmNotFoundException("Message does not exist!"));

        messagePatternRepository.delete(messagePattern);
    }
}
