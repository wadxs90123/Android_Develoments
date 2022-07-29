package com.example.lab8;

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

    private String QuestName;
    private int PayOff;
    private String Content;
    private String Location;
    private String PayMethod;
    private String Date;
    private Long currentTime;

    public Quest(){}
    public Quest(String PosterName,String ReceiverName,String QuestName,int Payoff,String Content,String Location,String PayMethod,String Date){
        this.PosterName = PosterName;
        this.ReceiverName = ReceiverName;
        this.QuestName = QuestName;
        this.PayOff = Payoff;
        this.Content = Content;
        this.Location = Location;
        this.PayMethod = PayMethod;
        this.Date = Date;
        this.isTaken = false;
        currentTime = System.currentTimeMillis();
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
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
