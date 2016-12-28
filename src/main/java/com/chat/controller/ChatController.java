package com.chat.controller;

import com.chat.websocket.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by xiangtianyu on 2016/12/26.
 */
@CrossOrigin
@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message/send/{userId}")
    public String sendMessage(@DestinationVariable String userId, Message message) {
        String des = "/message/get/" + userId;
        messagingTemplate.convertAndSend(des, message);
        return "SUCCESS";
    }
}
