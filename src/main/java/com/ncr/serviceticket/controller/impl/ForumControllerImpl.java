package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.controller.ForumController;
import com.ncr.serviceticket.dto.ForumMessageDto;
import com.ncr.serviceticket.model.ForumMessage;
import com.ncr.serviceticket.service.ForumMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ForumControllerImpl implements ForumController {

    private final ForumMessageService forumMessageService;

    @Override
    public ResponseEntity<Void> addForumMessage(ForumMessageDto addForumMessageDto, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        if(authEmail.equals(addForumMessageDto.email())) {
            forumMessageService.addForumMessage(addForumMessageDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Void> deleteForumMessageById(long id, Authentication authentication) {
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String authEmail = userDetails.getUsername();

        final ForumMessage forumMessage = forumMessageService.findById(id);
        final String forumMessageEmailIdentity = forumMessage.getForumOperator().getEmail();

        if(forumMessageEmailIdentity.equals(authEmail)) {
            forumMessageService.deleteForumMessageById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<List<ForumMessage>> getAllForumMessages() {
        return ResponseEntity.ok(forumMessageService.getAllForumMessages());
    }
}
