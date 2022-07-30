package com.example.lab8.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class User {


    private String username;
    private String password;
    private Message message;
    public User(){

    }
    public User(String username , String password){
        this.username = username;
        this.password = password;

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
