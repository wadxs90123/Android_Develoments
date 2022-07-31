package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.databinding.ActivityTakenInQuestBinding;
import com.example.lab8.models.Quest;

public class TakenInQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_in_quest);
        ActivityTakenInQuestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_taken_in_quest);

        String ID = getIntent().getStringExtra("ID");
        Quest quest = FirebaseUtil.getQuest(ID);
        binding.InQuestTitleText.setText(quest.getQuestName());
        binding.inQuestContentText.setText(quest.getContent());
        binding.inQuestPayoffText.setText("$"+quest.getPayOff());
        binding.inQuestDateText.setText(quest.getDate());
        binding.inQuestTimeText.setText(quest.getTime());
        binding.InQuestLocationText.setText(quest.getLocation());

        binding.InQuestBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,TakenWorksActivity.class);
            startActivity(intent);
        });

//        if(quest.getReceiverName()!=null){
//            binding.AskButton.setEnabled(true);
//        }else{
//            binding.AskButton.setEnabled(false);
//        }
        binding.AskButton.setOnClickListener(view->{
            //TODO AskWindow
        });
        binding.TakeQuest.setOnClickListener(view -> {//實際上是刪除 我懶的改...
            FirebaseUtil.QuitQuest(quest);
            Intent intent = new Intent(this,PostWorksActivity.class);
            startActivity(intent);
        });
    }
}