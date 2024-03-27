package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/message")
public interface MessageController {

    @PostMapping("/add")
    ResponseEntity<Void> addMessage(@RequestBody @Valid AddMessageDto addMessageDto);

    @PostMapping("/remove")
    ResponseEntity<Void> removeMessage(@RequestBody @Valid RemoveMessageDto removeMessageDto);
}
