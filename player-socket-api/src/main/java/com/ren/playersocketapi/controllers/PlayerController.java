package com.ren.playersocketapi.controllers;

import com.ren.playersocketapi.background.PlayerDataListener;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

//https://www.youtube.com/watch?v=OK2Fn6k7pwo
@Controller
public class PlayerController {

    @Autowired
    private PlayerDataListener backgroundListener;

    @PostConstruct
    public void setup(){
        Thread t = new Thread(backgroundListener);
        t.start();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        return message;
    }
}
