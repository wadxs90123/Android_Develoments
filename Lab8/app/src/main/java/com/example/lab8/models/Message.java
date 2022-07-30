package com.example.lab8.models;

import java.util.ArrayList;

public class Message {
    private String Sender;
    private String Receiver;
    private ArrayList<String> messages = new ArrayList<>();

    public Message(){}
    public Message(String Sender,String Receiver, ArrayList<String> messages){
        this.Receiver =Receiver;
        this.Sender = Sender;
        this.messages = messages;
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


    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
