package com.example.lab2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    ActivityMainBinding binding;//class名稱是根據Activity的名稱變動

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.setData(myViewModel);//綁定data

        binding.setLifecycleOwner(this);//一定要加
    }
}