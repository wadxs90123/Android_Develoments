package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;

import com.example.lab8.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static NavHostFragment fragment;
    private static NavController navController;
    private static BottomNavigationView bottomNavigationView;
    public static Activity activity;
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