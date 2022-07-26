package com.example.lab8;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.lab8.Adapters.MessageAdapter;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;
import com.example.lab8.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FirebaseUtil {
    public static String loginUsername;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static DatabaseReference Online = FirebaseDatabase.getInstance().getReference("Online");

    public static DatabaseReference users = FirebaseDatabase.getInstance().getReference("Users");
    public static DatabaseReference quests = FirebaseDatabase.getInstance().getReference("Quest");

    public static DatabaseReference messages = FirebaseDatabase.getInstance().getReference("Messages");
//    public static ArrayList<Quest> PostQuestStore = new ArrayList<>();
//    public static ArrayList<Quest> TakenQuestStore = new ArrayList<>();
    public static ArrayList<Message> MessageStore = new ArrayList<>();
    public static ArrayList<User> UserStore = new ArrayList<>();
    public static ArrayList<Quest> QuestStore = new ArrayList<>();
    public static ArrayList<String> Onlines = new ArrayList<>();

    public static void ListenerStart(){
        Online.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Onlines.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.getValue(String.class).equals("active")){
                        Onlines.add(ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        messages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MessageStore.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    MessageStore.add(ds.getValue(Message.class));
                }
                CommentFragment.setup();

                MessageActivity.setup();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserStore.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    UserStore.add(ds.getValue(User.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        quests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuestStore.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    QuestStore.add(ds.getValue(Quest.class));
                }
                WorkFragment.setup();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static boolean isOnline(String Username){
        return Onlines.contains(Username);
    }
    public static void login(String Username){
        Online.child(Username).setValue("active");
    }
    public static void logout(String Username){
        Online.child(Username).setValue("inactive");
        FirebaseUtil.loginUsername = null;
    }
    public static Quest getQuest(String ID){
        for(Quest q : QuestStore){
            if(q.getId().equals(ID)){
                return q;
            }
        }
        return null;
    }
    public static boolean hasQuest(String PosterName,String ReceiverName){
        for(Quest q : QuestStore){
            if(q.isTaken()) {
                if ((q.getPosterName().equals(PosterName) && q.getReceiverName().equals(ReceiverName)) || ((q.getPosterName().equals(ReceiverName) && q.getReceiverName().equals(PosterName)))) {
                    return true;
                }
            }
        }
        return false;
    }
    public static Quest findQuest(String PosterName,String ReceiverName){


        for(Quest q : QuestStore){
            if(q.isTaken()) {
                if (q.getPosterName().equals(PosterName) && q.getReceiverName().equals(ReceiverName)) {
                    return q;
                }
            }
        }
        return null;
    }
    public static void addPoint(String username,int value){
        User user = getUser(username);
        user.setPoint(user.getPoint()+value);
        users.child(username).setValue(user);
    }
    public static void deleteMessages(String sender,String rec){
        ArrayList<Message> tM = new ArrayList<>();
        for(Message m : MessageStore){
            if((m.getReceiver().equals(sender)&&m.getSender().equals(rec))||(m.getReceiver().equals(rec)&&m.getSender().equals(sender))){
                tM.add(m);
            }
        }
        for(Message m : tM){
            messages.child(m.getId()).removeValue();
        }
    }
    public static void sendMessage(String sender, String receiver, String msg){
        String id = messages.push().getKey();
        Message message = new Message(id,sender,receiver,msg);
        messages.child(id).setValue(message);
    }
    public static void addQuest(String PosterName,String ReceiverName,String QuestName,String Content ,String Date,String time,double Lat,double Lon,double Taker_lat,double Taker_lon){
        String id = quests.push().getKey();
        Quest quest = new Quest(id, PosterName,null,QuestName,Content,Date,time,Lat,Lon,Taker_lat,Taker_lon);
        //getUser(PosterName).getPostQuests().add(quest);
        quests.child(id).setValue(quest);
    }
    public static void AdaptQuest(Quest quest){//應徵任務
        if(quest.isTaken()){
            Toast.makeText(MainActivity.activity.getApplicationContext(),"這個任務已被接走了",Toast.LENGTH_SHORT).show();
        }else{
            if(quest.getPosterName().equals(loginUsername)){//是自己的
                Toast.makeText(MainActivity.activity.getApplicationContext(),"請不要接自己發布的任務!",Toast.LENGTH_SHORT).show();
                return;
            }
            quest.setTaken(true);
            quest.setReceiverName(loginUsername);
            quests.child(quest.getId()).setValue(quest);
        }
    }
    public static void UpdateQuest(Quest quest){//應徵任務
        quests.child(quest.getId()).setValue(quest);
    }
    public static void UpdateUser(User user){
        users.child(user.getUsername()).setValue(user);
    }
    public static void QuitQuest(Quest quest){
        quest.setReceiverName(null);
        quest.setTaken(false);
        quests.child(quest.getId()).setValue(quest);
    }
    public static void deleteQuest(Quest quest){
        quests.child(quest.getId()).removeValue();
    }
    public static void addUser(String username,String password){
        User user = new User(username,password);
        users.child(user.getUsername()).setValue(user);
    }
    public static boolean isUserExist(String username){
        for(User user : UserStore){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static User getUser(String username){
        for(User user : UserStore){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
