package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        binding.PosterName.setText(quest.getPosterName());
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
             Intent intent = new Intent(this, MessageActivity.class);
             intent.putExtra("Name",quest.getPosterName());
             startActivity(intent);
        });
        binding.TakeQuest.setOnClickListener(view -> {//實際上是刪除 我懶的改...
            FirebaseUtil.QuitQuest(quest);
            Toast.makeText(getApplicationContext(),"已放棄此任務", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,TakenWorksActivity.class);
            startActivity(intent);
        });
        binding.button8.setOnClickListener(view -> {
            FirebaseUtil.deleteQuest(quest);
            Toast.makeText(getApplicationContext(),"恭喜!已完成此任務", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,TakenWorksActivity.class);
            startActivity(intent);
        });
    }
}