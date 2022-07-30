package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab8.databinding.ActivityInQuestBinding;
import com.example.lab8.models.Quest;


public class InQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_quest);
        ActivityInQuestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_in_quest);
        int position = getIntent().getIntExtra("Position",0);
        Quest quest = FirebaseUtil.QuestStore.get(position);
        binding.InQuestTitleText.setText(quest.getQuestName());
        binding.inQuestContentText.setText(quest.getContent());
        binding.inQuestPayoffText.setText("$"+quest.getPayOff());
        binding.inQuestDateText.setText(quest.getDate());
        binding.inQuestTimeText.setText(quest.getTime());
        binding.inQuestPosterText.setText(quest.getPosterName());
        binding.InQuestLocationText.setText(quest.getLocation());

        if(quest.getPosterName().equals(FirebaseUtil.loginUsername)){
            binding.AskButton.setEnabled(false);
            binding.TakeQuest.setEnabled(false);
        }else{
            binding.TakeQuest.setEnabled(true);
            binding.AskButton.setEnabled(true);
        }

        binding.InQuestBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        binding.AskButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,MessageActivity.class);
            intent.putExtra("ID",quest.getId());
            intent.putExtra("Name",quest.getPosterName());
            startActivity(intent);
        });

        binding.TakeQuest.setOnClickListener(view -> {
            FirebaseUtil.AdaptQuest(quest);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }
}