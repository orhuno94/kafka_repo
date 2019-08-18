package com.example.consumer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.example.consumer.mongodb.Message;
import com.example.consumer.mongodb.MessageRepository;

@Component
public class Listener {

	@Autowired
	private MessageRepository repository;
    @Autowired
    private SimpMessagingTemplate template;
    
    @StreamListener(target = "inbound")
    public void processMessage(Message pushMessage){
        this.template.convertAndSend("/topic/pushNotification", pushMessage);
    	repository.save(pushMessage);
    	System.out.println("Message Saved!!");
    }
}
