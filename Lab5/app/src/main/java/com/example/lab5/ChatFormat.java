package com.example.lab5;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class ChatFormat{
    private String EventName;
    private String snd_name;
    private String message;
    private String payoff;
    private String pos;
    private String timestamp;

    public ChatFormat(String EventName, String snd_name, String payoff, String pos, String message) {
        this.EventName = EventName;
        this.snd_name = snd_name;
        this.payoff = payoff;
        this.pos = pos;
        this.message = message;
        timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }
    public ChatFormat(){}


    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getSnd_name() {
        return snd_name;
    }

    public void setSnd_name(String snd_name) {
        this.snd_name = snd_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayoff() {
        return payoff;
    }

    public void setPayoff(String payoff) {
        this.payoff = payoff;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
