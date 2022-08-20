package com.example.lab8.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class User {


    private String username;
    private String password;
    private int Point;
    private double Lat;
    private double Lon;
    public User(){

    }
    public User(String username , String password){
        this.username = username;
        this.password = password;
        this.Lat = 0;
        this.Lon = 0;
        this.Point = 0;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
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
