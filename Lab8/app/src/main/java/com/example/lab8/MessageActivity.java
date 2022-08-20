package com.example.lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.lab8.Adapters.MessageAdapter;
import com.example.lab8.databinding.ActivityMessageBinding;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;
import com.example.lab8.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    public static RecyclerView recycler_view;
    public static String PosterName;
    public static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = true;
        setContentView(R.layout.activity_message);
        ActivityMessageBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_message);
        setTitle("聊天室");

        String ID = getIntent().getStringExtra("ID");
        PosterName = getIntent().getStringExtra("Name");


        binding.textName.setText(PosterName);



        binding.imageBack.setOnClickListener(view->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        binding.sendButton.setOnClickListener(view -> {
            if(binding.inputMessage.getText()==null||binding.inputMessage.getText().toString().equals("")){
                return;
            }
            FirebaseUtil.sendMessage(FirebaseUtil.loginUsername,PosterName,binding.inputMessage.getText().toString());
            binding.inputMessage.setText(null);
        });
//

        binding.imageButton.setOnClickListener(view ->{
            FirebaseUtil.sendMessage(FirebaseUtil.loginUsername,PosterName,"DefaultImage");
        });
//         連結元件
        recycler_view = (RecyclerView) binding.chatRecyclerView;
//         設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        ((LinearLayoutManager)recycler_view.getLayoutManager()).setStackFromEnd(true);
//         設置格線
//         recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        setup();
//         將資料交給adapter

    }
    public static void sendImage(ImageView image){
        String url = "https://cdn2.ettoday.net/images/4064/4064694.jpg";
        image.setImageDrawable(loadImageFromURL(url));
        image.setVisibility(View.VISIBLE);
    }

    private static Drawable loadImageFromURL(String url) {
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable draw = Drawable.createFromStream(is, "src");

            return draw;
        }catch (Exception e) {
            //TODO handle error
            Log.i("loadingImg", e.toString());
            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        flag = false;

    }

    public static void setup(){
        if(!flag){return;}
        ArrayList<Message> messages = new ArrayList<>();
        int size = FirebaseUtil.MessageStore.size()-1;
        for(int i =0 ; i <=size; i ++){
            Message message = FirebaseUtil.MessageStore.get(i);
            if((message.getSender().equals(FirebaseUtil.loginUsername)&&message.getReceiver().equals(PosterName))||
                    (message.getSender().equals(PosterName)&&message.getReceiver().equals(FirebaseUtil.loginUsername))){
                messages.add(message);
            }
        }

        MessageAdapter messageAdapter = new MessageAdapter(messages);

        recycler_view.setAdapter(messageAdapter);
    }
}