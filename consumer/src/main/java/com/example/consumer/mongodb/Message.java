package com.example.consumer.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message {
	
	@Id
    public String id;
    private String datetime;

    private String level;

    private String server;

	private String detail;

	
	public Message(String id, String datetime, String level, String server, String detail) {
		this.id=id;
	    this.datetime = datetime;
	    this.level = level;
	    this.server = server;
	    this.detail = detail;
	  }
	
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
