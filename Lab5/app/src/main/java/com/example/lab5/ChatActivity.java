package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    DatabaseReference database;
    List<ChatFormat> chats;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("name");

        setContentView(R.layout.activity_chat);

        chats = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference("Message");

        listView = findViewById(R.id.LV);

        findViewById(R.id.Add_mission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddMissionActivity.class);
                intent.putExtra("name",username);
                ChatActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        Log.d("Mytag", "onDataChange: "+dataSnapshot1.toString());
                        chats.add(dataSnapshot1.getValue(ChatFormat.class));
                    }
                }
                UserList list = new UserList(ChatActivity.this,chats);
                listView.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
