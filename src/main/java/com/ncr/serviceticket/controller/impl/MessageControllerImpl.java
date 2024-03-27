package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.MessageController;
import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import com.ncr.serviceticket.service.impl.OperatorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {

    private final OperatorServiceImpl operatorService;

    @Override
    public ResponseEntity<Void> addMessage(AddMessageDto addMessageDto) {
        operatorService.addMessage(addMessageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> removeMessage(RemoveMessageDto removeMessageDto) {
        operatorService.removeMessage(removeMessageDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
