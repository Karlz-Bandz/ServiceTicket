package com.ncr.serviceticket.service.impl;

import com.ncr.serviceticket.dto.AddForumMessageDto;
import com.ncr.serviceticket.exception.atm.AtmNotFoundException;
import com.ncr.serviceticket.model.ForumMessage;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.repo.ForumMessageRepository;
import com.ncr.serviceticket.repo.OperatorRepository;
import com.ncr.serviceticket.service.ForumMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumMessageServiceImpl implements ForumMessageService {

    private final OperatorRepository operatorRepository;

    private final ForumMessageRepository forumMessageRepository;

    @Override
    public void addForumMessage(AddForumMessageDto addChatMessageDto) {
        final Date currentDate = new Date();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        final Operator operator = operatorRepository.findByEmail(addChatMessageDto.getEmail())
                .orElseThrow(() -> new AtmNotFoundException("Operator with provided email doesn't exist!"));

        final ForumMessage chatMessage = ForumMessage.builder()
                .message(addChatMessageDto.getMessage())
                .date(simpleDateFormat.format(currentDate))
                .forumOperator(operator)
                .build();

        forumMessageRepository.save(chatMessage);
    }

    @Override
    public void deleteForumMessageById(long id) {
        final ForumMessage chatMessage = forumMessageRepository.findById(id)
                .orElseThrow(() -> new AtmNotFoundException("Provided message doesn't exist!"));

        forumMessageRepository.delete(chatMessage);
    }

    @Override
    public List<ForumMessage> getAllForumMessages() {
        return forumMessageRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(ForumMessage::getId).reversed())
                .limit(10)
                .toList();
    }
}
