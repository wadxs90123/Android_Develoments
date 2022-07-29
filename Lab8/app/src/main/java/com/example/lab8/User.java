package com.example.lab8;

import java.util.ArrayList;

public class User {

    private int post_quest;//發布的任務數量
    private int receive_quest;//接受的任務數量

    private ArrayList<Quest> postQuests = new ArrayList<>();
    private ArrayList<Quest> receiveQuests = new ArrayList<>();

    private String username;
    private String password;

    public User(){

    }
    public User(String username , String password){
        this.username = username;
        this.password = password;
    }

    public ArrayList<Quest> getPostQuests() {
        return postQuests;
    }

    public void setPostQuests(ArrayList<Quest> postQuests) {
        this.postQuests = postQuests;
    }

    public ArrayList<Quest> getReceiveQuests() {
        return receiveQuests;
    }

    public void setReceiveQuests(ArrayList<Quest> receiveQuests) {
        this.receiveQuests = receiveQuests;
    }

    public int getPost_quest() {
        return post_quest;
    }

    public void setPost_quest(int post_quest) {
        this.post_quest = post_quest;
    }

    public int getReceive_quest() {
        return receive_quest;
    }

    public void setReceive_quest(int receive_quest) {
        this.receive_quest = receive_quest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
