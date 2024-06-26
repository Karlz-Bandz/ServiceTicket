package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.MessageController;
import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.CheckMessageDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.MessagePattern;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.service.MessageService;
import com.ncr.serviceticket.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {

    private final OperatorService operatorService;

    private final MessageService messageService;

    @Override
    public ResponseEntity<Void> changeMessageById(AddMessageDto addMessageDto, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        List<Long> messagesId = messageService.findEachIdByEmail(authEmail);

        if (messagesId.contains(addMessageDto.getId())) {

            MessagePattern messagePattern = MessagePattern.builder()
                    .id(addMessageDto.getId())
                    .message(addMessageDto.getMessage())
                    .title(addMessageDto.getTitle())
                    .build();

            messageService.changeMessageById(messagePattern);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<List<CheckMessageDto>> getAllMessages(String email, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        if (email.equals(authEmail)) {
            return ResponseEntity.ok(messageService.getAllMessagesByEmail(email));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Void> addMessage(AddMessageDto addMessageDto, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        if (addMessageDto.getEmail().equals(authEmail)) {

            final Operator operator = operatorService.findByEmail(addMessageDto.getEmail())
                    .orElseThrow(() -> new AtmNotFoundException("User not found!"));

            MessagePattern messagePattern = MessagePattern.builder()
                    .title(addMessageDto.getTitle())
                    .message(addMessageDto.getMessage())
                    .operator(operator)
                    .build();

            messageService.addMessage(messagePattern);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Void> removeMessage(long id, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        List<Long> messagesId = messageService.findEachIdByEmail(authEmail);

        if (messagesId.contains(id)) {

            messageService.deleteMessage(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
