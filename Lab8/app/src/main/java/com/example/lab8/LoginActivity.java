package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUtil.ListenerStart();

        setContentView(R.layout.activity_login);

        setTitle("登入註冊");

        EditText user_n = findViewById(R.id.editTextTextPersonName);
        EditText user_p = findViewById(R.id.editTextTextPassword);


        findViewById(R.id.button).setOnClickListener(view -> {
            String username = user_n.getText().toString();
            String password = user_p.getText().toString();
            if(username.length()== 0||password.length()==0){
                Toast.makeText(getApplicationContext(), "帳號或密碼的欄位不能為空!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(FirebaseUtil.isUserExist(username)){
                if(FirebaseUtil.getUser(username).getPassword().equals(password)) {
                    if(!FirebaseUtil.isOnline(username)) {
                        FirebaseUtil.loginUsername = username;
                        FirebaseUtil.login(username);
                        Toast.makeText(getApplicationContext(), "歡迎使用垃圾通, " + username, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "該名用戶已在線上!請確認後再試一遍",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "密碼錯誤!",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"該用戶不存在",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(view -> {
            String username = user_n.getText().toString();
            String password = user_p.getText().toString();
            if(username.length()== 0||password.length()==0){
                Toast.makeText(getApplicationContext(), "帳號或密碼的欄位不能為空!",Toast.LENGTH_SHORT).show();
                return;
            }
            if(FirebaseUtil.isUserExist(username)){
                Toast.makeText(getApplicationContext(),"註冊失敗,該用戶已存在",Toast.LENGTH_SHORT).show();
            }else{
                FirebaseUtil.addUser(username,password);
                Toast.makeText(getApplicationContext(),"註冊成功!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}