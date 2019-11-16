package com.example.mvp.sevice;

import com.example.mvp.domain.Message;
import com.example.mvp.domain.User;
import com.example.mvp.domain.dto.MessageDto;
import com.example.mvp.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;


@Controller
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;


    public Page<MessageDto> messageList(Pageable pageable, String filter,User user)
    {
        if (filter != null && !filter.isEmpty()) {
            return  messageRepo.findByTag(filter, pageable,user);
        } else {
            return messageRepo.findAll(pageable,user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable,author,currentUser);
    }
}
