package com.ren.playersocketapi.broadcast;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
    This module is used to broadcast messages about a specific topic (players) to all client (listening sockets)
    applications listening to that topic
 */
@Component
//@EnableScheduling
//@Configuration
public class PlayerStatBroadcaster {

    @Autowired
    SimpMessagingTemplate template;
    private String playerTopics = "/topic/messages";

    /**
     * Data will be marshalled when transmitting content.
     * @param jsonContent
     */
    public void broadcastMessage(String jsonContent){
        template.convertAndSend(playerTopics, jsonContent);
    }

    /*
    @Scheduled(fixedDelay = 5000)
    public void broadcastMessage(){
        System.out.println("Seeing a message to listening clients");
        String message = "{\"from\":\"Server\",\"text\":\"Hi from Server\"}";
        template.convertAndSend(playerTopics, message);
    }
     */
}
