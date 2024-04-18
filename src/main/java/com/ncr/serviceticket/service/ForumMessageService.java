package com.ncr.serviceticket.service;

import com.ncr.serviceticket.dto.AddForumMessageDto;
import com.ncr.serviceticket.model.ForumMessage;

import java.util.List;

public interface ForumMessageService {

    void addForumMessage(AddForumMessageDto addChatMessageDto);

    void deleteForumMessageById(long id);

    List<ForumMessage> getAllForumMessages();
}
