package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.CheckMessageDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.repo.MessagePatternRepository;
import com.ncr.serviceticket.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessagePatternRepository messagePatternRepository;

    @Override
    public List<CheckMessageDto> getAllMessagesByEmail(String email) {
        return messagePatternRepository.findByOwnerUsername(email);
    }

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
