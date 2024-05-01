package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.ForumMessageDto;
import com.ncr.serviceticket.model.ForumMessage;

import java.util.List;

public interface ForumMessageService {

    ForumMessage findById(long id);

    void addForumMessage(ForumMessageDto addChatMessageDto);

    void deleteForumMessageById(long id);

    List<ForumMessage> getAllForumMessages();
}