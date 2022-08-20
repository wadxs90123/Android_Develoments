package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.googlemap.CheckMapFragment;
import com.example.lab8.googlemap.MapsFragment;
import com.example.lab8.models.User;

public class CheckMap extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_map);

        User user = FirebaseUtil.getUser(getIntent().getStringExtra("Name"));

        findViewById(R.id.button9).setOnClickListener(view->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,new CheckMapFragment(user));
        fragmentTransaction.commit();
    }
}