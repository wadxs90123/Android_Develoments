package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.databinding.ActivityMessageBinding;
import com.example.lab8.models.Quest;
import com.example.lab8.models.User;

public class MessageActivity extends AppCompatActivity {
    RecyclerView recycler_view;
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


        // 連結元件
        recycler_view = (RecyclerView) binding.chatRecyclerView;
        // 設置RecyclerView為列表型態
//        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        // 設置格線
        // recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // 將資料交給adapter
//        workAdapter = new MessageAdapter(FirebaseUtil.QuestStore,this);
        // 設置adapter給recycler_view
//        recycler_view.setAdapter(workAdapter);
    }
}