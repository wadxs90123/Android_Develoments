package com.example.lab8.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Quest {
//    1.任務範例:
//    發布人名稱:test1
//    任務名稱: 急！誠徵幫手倒垃圾
//    任務酬勞: $100
//    任務內容: 幫我丟垃圾
//    任務地點: 高雄市前鎮區成功路二段39號
//    支薪方式: 匯款
//    任務時間: 2022/07/27~2022/07/31
    private String PosterName;
    private String ReceiverName;

    private boolean isTaken;//是否被接取了
    private double Lat;
    private double Lon;
    private String id;
    private String QuestName;
    private int PayOff;
    private String Content;
    private String Date;
    private Long currentTime;
    private String time;

    public Quest(){}
    public Quest(String id,String PosterName,String ReceiverName,String QuestName,int Payoff,String Content,String Date,String time,double Lat,double Lon){
        this.id = id;
        this.PosterName = PosterName;
        this.ReceiverName = ReceiverName;
        this.QuestName = QuestName;
        this.PayOff = Payoff;
        this.Content = Content;
        this.Date = Date;
        this.time = time;
        this.isTaken = false;
        currentTime = System.currentTimeMillis();
        this.Lat = Lat;
        this.Lon = Lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPosterName() {
        return PosterName;
    }

    public void setPosterName(String posterName) {
        PosterName = posterName;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getQuestName() {
        return QuestName;
    }

    public void setQuestName(String questName) {
        QuestName = questName;
    }

    public int getPayOff() {
        return PayOff;
    }

    public void setPayOff(int payOff) {
        PayOff = payOff;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}
