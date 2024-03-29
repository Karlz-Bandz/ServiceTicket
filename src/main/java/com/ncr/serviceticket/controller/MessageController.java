package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AddMessageDto;
import com.ncr.serviceticket.dto.CheckMessageDto;
import com.ncr.serviceticket.dto.RemoveMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/message")
public interface MessageController {

    @GetMapping("/all/{email}")
    ResponseEntity<List<CheckMessageDto>> getAllMessages(@PathVariable("email") String email, Authentication authentication);

    @PostMapping("/add")
    ResponseEntity<Void> addMessage(@RequestBody AddMessageDto addMessageDto, Authentication authentication);

    @DeleteMapping("/delete")
    ResponseEntity<Void> removeMessage(@RequestBody RemoveMessageDto removeMessageDto, Authentication authentication);
}
