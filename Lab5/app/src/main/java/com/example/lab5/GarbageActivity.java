package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GarbageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage);
        TextView tv = findViewById(R.id.missionname);
        TextView snd = findViewById(R.id.snd_name_imf_in_mission_board);
        TextView payoff = findViewById(R.id.payoff_imf_in_mission_board);
        TextView message = findViewById(R.id.message_imf_in_mission_board);
        TextView pos = findViewById(R.id.pos_imf_in_mission_board);


        tv.setText(getIntent().getStringExtra("name"));
        snd.setText(getIntent().getStringExtra("snd"));
        payoff.setText(getIntent().getStringExtra("payoff"));
        message.setText(getIntent().getStringExtra("message"));
        pos.setText(getIntent().getStringExtra("pos"));

        Button back = findViewById(R.id.button3);
        Button admit =findViewById(R.id.button2);
        back.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
            startActivity(intent);
        });
    }
}