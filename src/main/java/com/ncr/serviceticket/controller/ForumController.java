package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AddForumMessageDto;
import com.ncr.serviceticket.model.ForumMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/forum")
public interface ForumController {

    @PostMapping("/add")
    ResponseEntity<Void> addForumMessage(@RequestBody AddForumMessageDto addForumMessageDto, Authentication authentication);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteForumMessageById(@PathVariable("id") long id);

    @GetMapping("/all")
    ResponseEntity<List<ForumMessage>> getAllForumMessages();
}
