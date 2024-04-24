package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/message")
public interface MessageController {

    @PutMapping("/change")
    ResponseEntity<Void> changeMessageById(@RequestBody MessageDto addMessageDto, Authentication authentication);

    @GetMapping("/{email}/all")
    ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable("email") String email, Authentication authentication);

    @PostMapping("/add")
    ResponseEntity<Void> addMessage(@RequestBody MessageDto addMessageDto, Authentication authentication);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> removeMessage(@PathVariable("id") long id, Authentication authentication);
}
