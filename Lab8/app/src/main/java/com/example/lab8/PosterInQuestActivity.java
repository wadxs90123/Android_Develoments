package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lab8.databinding.ActivityPosterInQuestBinding;
import com.example.lab8.models.Quest;

import java.text.DecimalFormat;

public class PosterInQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_in_quest);
        ActivityPosterInQuestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_poster_in_quest);
        setTitle("工作內容");


        String ID = getIntent().getStringExtra("ID");
        Quest quest = FirebaseUtil.getQuest(ID);



        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        String Lat_s = decimalFormat.format(quest.getLat());
        String Lon_s = decimalFormat.format(quest.getLon());
        binding.InQuestLocationText.setText("經緯度("+Lon_s+" , "+Lat_s+")");


        binding.TakenName.setText(quest.getReceiverName());
        binding.InQuestTitleText.setText(quest.getQuestName());
        binding.inQuestContentText.setText(quest.getContent());
//        binding.inQuestPayoffText.setText("$"+quest.getPayOff());
        binding.inQuestDateText.setText(quest.getDate());
        binding.inQuestTimeText.setText(quest.getTime());


        binding.InQuestBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,PostWorksActivity.class);
            startActivity(intent);
        });

        if(quest.getReceiverName()!=null){
            binding.AskButton.setEnabled(true);
        }else{
            binding.AskButton.setEnabled(false);
        }

        binding.AskButton.setOnClickListener(view->{
            Intent intent = new Intent(this, MessageActivity.class);
            intent.putExtra("Name",quest.getReceiverName());
            startActivity(intent);
        });
        binding.TakeQuest.setOnClickListener(view -> {//實際上是刪除 我懶的改...
            FirebaseUtil.deleteQuest(quest);
            Toast.makeText(getApplicationContext(),"已刪除此任務", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,PostWorksActivity.class);
            startActivity(intent);
        });
    }
}