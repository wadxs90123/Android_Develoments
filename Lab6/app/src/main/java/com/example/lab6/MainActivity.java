package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);//找到navcontroller
        NavController navController = fragment.getNavController();//現在用這個方法 UI那邊已經無法直接使用NavController了 2022.7.29

        NavigationUI.setupActionBarWithNavController(this,navController);//建立NavUI
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);//找到navcontroller
        NavController navController = fragment.getNavController();

        return navController.navigateUp();
    }
}
