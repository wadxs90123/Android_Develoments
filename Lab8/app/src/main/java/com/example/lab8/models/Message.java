package com.example.lab8.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Message {
    private String id;
    private String Sender;
    private String Receiver;
    private String timestamp;
    private String msg;

    public Message() {}

    public Message(String id , String Sender, String Receiver, String msg) {
        this.id = id;
        this.Receiver = Receiver;
        this.Sender = Sender;
        this.msg = msg;
        timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

}
