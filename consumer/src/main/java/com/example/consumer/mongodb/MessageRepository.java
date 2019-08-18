package com.example.consumer.mongodb;

import java.util.List;
public interface MessageRepository {

    public void save(Message message);
    public List<Message> find(String start_date, String end_date, String server, String level);
    public List<Message> findAll();
}