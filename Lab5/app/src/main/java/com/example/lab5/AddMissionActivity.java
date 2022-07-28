package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AddMissionActivity extends AppCompatActivity {

    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);

        TextView username = findViewById(R.id.snd_name);

        TextView EventName = findViewById(R.id.editTextTextPersonName2);
        TextView payoff = findViewById(R.id.editTextTextPersonName3);
        TextView pos = findViewById(R.id.editTextTextPersonName4);
        TextView message = findViewById(R.id.editTextTextPersonName6);

        user_name = getIntent().getStringExtra("name");

        username.setText(user_name);

        findViewById(R.id.snd_mission).setOnClickListener(v->{
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Message");

            ChatFormat ch = new ChatFormat(EventName.getText().toString(),user_name,payoff.getText().toString(),pos.getText().toString(),message.getText().toString());


            Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(intent);
        });

    }
}