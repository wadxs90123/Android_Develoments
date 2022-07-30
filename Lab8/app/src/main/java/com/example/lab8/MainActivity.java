package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static NavHostFragment fragment;
    private static NavController navController;
    private static BottomNavigationView bottomNavigationView;
    private static Activity activity;
    private static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);//找到navcontroller
        navController = fragment.getNavController();//現在用這個方法 UI那邊已經無法直接使用NavController了 2022.7.29

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();

        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);//將導航和下面的那排按鈕們結合

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);//找到navcontroller

        NavController controller = fragment.getNavController();
        return controller.navigateUp();
    }
    public static void setUser(User u){
        user = u;
    }
}