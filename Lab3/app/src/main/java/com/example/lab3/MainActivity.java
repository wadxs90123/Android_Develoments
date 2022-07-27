package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyData myData = new MyData(getApplicationContext());//傳遞this 會有 memory leak 的可能
        myData.number = 10020;
        myData.save();

        String TAG = "MyTag";

        int y = myData.load();
        Log.d(TAG,"onCreate : "+ y);

    }
}