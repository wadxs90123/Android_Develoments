package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b1 = (Button)findViewById(R.id.b1);

        Button b2 = (Button)findViewById(R.id.button2);

        TextView t1 = (TextView) findViewById(R.id.textView);

        Intent intent = new Intent(this,MainActivity2.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().equals("按三小")){
                    t1.setText("就叫你別按");
                }else{
                    t1.setText("按三小");
                }
                b1.setText("別按");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

     }
}