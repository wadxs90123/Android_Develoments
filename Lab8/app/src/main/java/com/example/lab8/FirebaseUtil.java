package com.example.lab8;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseUtil {
    public static String loginUsername;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static DatabaseReference users = FirebaseDatabase.getInstance().getReference("Users");

    public static ArrayList<User> UserStore = new ArrayList<>();
    public static ArrayList<Quest> QuestStore = new ArrayList<>();

    public static void ListenerStart(){
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserStore.clear();
                QuestStore.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    UserStore.add(ds.getValue(User.class));
                    users.child(ds.getValue(User.class).getUsername()).child("Quests").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()){
                                QuestStore.add(ds.getValue(Quest.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static void addQuest(String PosterName,String ReceiverName,String QuestName,int Payoff,String Content,String Location,String Date,String time){
        DatabaseReference ref = users.child(PosterName).child("Quests");
        String id = ref.push().getKey();
        Quest quest = new Quest(id, PosterName,null,QuestName,Payoff,Content,Location,Date,time);
        //getUser(PosterName).getPostQuests().add(quest);
        ref.child(id).setValue(quest);
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
            DatabaseReference ref = users.child(quest.getPosterName()).child("Quests").child(quest.getId());
            ref.setValue(quest);
        }
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