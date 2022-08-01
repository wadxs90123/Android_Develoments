package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.Adapters.PostWorksAdapter;
import com.example.lab8.databinding.ActivityPostWorksBinding;
import com.example.lab8.models.Quest;

import java.util.ArrayList;



public class PostWorksActivity extends AppCompatActivity {
    ArrayList<Quest> mData = new ArrayList<>();
    private PostWorksAdapter postWorksAdapter;
    RecyclerView recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_works);

        setTitle("我發布的工作們");

        //Data Binding
        ActivityPostWorksBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_post_works);
        //binding.UserName.setText(FirebaseUtil.loginUsername);
        //Data Binding
        binding.button6.setOnClickListener(view->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        // 連結元件
        recycler_view = (RecyclerView) binding.PostList;
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // 設置格線
        // recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mData.clear();
        for(Quest q : FirebaseUtil.QuestStore){
            if(q.getPosterName().equals(FirebaseUtil.loginUsername)){
                mData.add(q);//如果是自己po的
            }
        }
        // 將資料交給adapter

        postWorksAdapter = new PostWorksAdapter(mData,this);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(postWorksAdapter);
    }
}