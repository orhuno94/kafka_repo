package com.example.consumer.controller;

import com.example.consumer.mongodb.Message;
import com.example.consumer.mongodb.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MessageController {

    @Autowired
    MessageRepositoryImpl repository;

    @RequestMapping("/GetMessages")
    public List<Message> GetMessages(
            @RequestParam(value = "server") String server,
            @RequestParam(value = "level") String level,
            @RequestParam(value = "startdate") String startdate,
            @RequestParam(value = "enddate") String enddate
    ) {
        System.out.println(server+" "+level+" "+startdate+" "+enddate);
        return repository.find(startdate, enddate, server, level);
    }
}
