package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Message");
//        ArrayList<ChatFormat> arrayList = new ArrayList<>();


        findViewById(R.id.button).setOnClickListener(v->{
            if(!((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString().equals("")){
//                database.getReference("Message/"+((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString()).setValue(new ChatFormat());
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                intent.putExtra("name",((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"請輸入名字!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}