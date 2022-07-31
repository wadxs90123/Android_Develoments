package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.Adapters.MessageAdapter;
import com.example.lab8.databinding.ActivityMessageBinding;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;
import com.example.lab8.models.User;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ActivityMessageBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_message);

        String ID = getIntent().getStringExtra("ID");
        String PosterName = getIntent().getStringExtra("Name");
        User Poster = FirebaseUtil.getUser(PosterName);
        Quest quest = FirebaseUtil.getQuest(ID);



        binding.textName.setText(PosterName);

        binding.imageBack.setOnClickListener(view->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        binding.sendButton.setOnClickListener(view -> {
            FirebaseUtil.sendMessage(FirebaseUtil.loginUsername,PosterName,binding.inputMessage.getText().toString());
        });

//         連結元件
        recycler_view = (RecyclerView) binding.chatRecyclerView;
//         設置RecyclerView為列表型態
//        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
//         設置格線
//         recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

//         將資料交給adapter
        ArrayList<Message> messages = new ArrayList<>();
        for(Message message : FirebaseUtil.MessageStore){
            if((message.getSender().equals(FirebaseUtil.loginUsername)&&message.getReceiver().equals(PosterName))||
                    (message.getSender().equals(PosterName)&&message.getReceiver().equals(FirebaseUtil.loginUsername))){
                messages.add(message);
            }
        }
        messageAdapter = new MessageAdapter(messages,this);
//         設置adapter給recycler_view
        recycler_view.setAdapter(messageAdapter);
    }
}