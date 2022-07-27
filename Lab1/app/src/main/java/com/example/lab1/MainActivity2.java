package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = new Intent(this,MainActivity.class);

        Button b1 = (Button) findViewById(R.id.button);
        Button back = (Button) findViewById(R.id.button3);
        EditText et = (EditText) findViewById(R.id.editTextTextPersonName);
        TextView tv = (TextView) findViewById(R.id.textView2);

        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                // calculation
                double value = Double.parseDouble(et.getText().toString());
                double km = 1.61 * value;
                tv.setText("結果: "+ km+" 公里");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //back idx
                startActivity(intent);
            }
        });
    }
}