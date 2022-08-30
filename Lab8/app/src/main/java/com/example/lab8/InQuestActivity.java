package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab8.databinding.ActivityInQuestBinding;
import com.example.lab8.models.Quest;
import com.example.lab8.models.User;

import java.text.DecimalFormat;


public class InQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_quest);
        ActivityInQuestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_in_quest);
        String id = getIntent().getStringExtra("ID");
        Quest quest = FirebaseUtil.getQuest(id);
        setTitle("工作內容");

        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        String Lat_s = "";
        String Lon_s = "";

        if(quest.getLat() != 0){
            Lat_s = decimalFormat.format(quest.getLat());
        }
        if(quest.getLon() != 0) {
            Lon_s = decimalFormat.format(quest.getLon());
        }
//
        double Taker_Lat = getIntent().getDoubleExtra("Lat",0);
        double Taker_Lon = getIntent().getDoubleExtra("Lon",0);
//
        quest.setTaker_Lat(Taker_Lat);
        quest.setTaker_Lon(Taker_Lon);

        FirebaseUtil.UpdateQuest(quest);
        User rec = FirebaseUtil.getUser(FirebaseUtil.loginUsername);
        String Taker_Lat_s = decimalFormat.format(rec.getLat());
        String Taker_Lon_s = decimalFormat.format(rec.getLon());

        binding.InQuestLocationText.setText("經緯度("+Lon_s+" , "+Lat_s+")");
        binding.TakerPos.setText("經緯度("+Taker_Lon_s+" , "+Taker_Lat_s+")");

        binding.InQuestTitleText.setText(quest.getQuestName());
        binding.inQuestContentText.setText(quest.getContent());
//        binding.inQuestPayoffText.setText("$"+quest.getPayOff());
        binding.inQuestDateText.setText(quest.getDate());
        binding.inQuestTimeText.setText(quest.getTime());
        binding.inQuestPosterText.setText(quest.getPosterName());

        if(quest.getPosterName().equals(FirebaseUtil.loginUsername)){
            binding.AskButton.setEnabled(false);
            binding.TakeQuest.setEnabled(false);

            binding.TakerPosTitle.setVisibility(View.GONE);
            binding.TakerPos.setVisibility(View.GONE);
//            binding.ChooseMap.setEnabled(false);
        }else{
            binding.TakeQuest.setEnabled(true);
            binding.AskButton.setEnabled(true);

            binding.TakerPosTitle.setVisibility(View.VISIBLE);
            binding.TakerPos.setVisibility(View.VISIBLE);
//            binding.ChooseMap.setEnabled(true);
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
            if(FirebaseUtil.getUser(FirebaseUtil.loginUsername).getLat()==0&&FirebaseUtil.getUser(FirebaseUtil.loginUsername).getLon()==0){
                Toast.makeText(view.getContext(),"請到\"我的\"頁面裡面的\"選擇所在地\"選擇自己的所在地後,再應徵任務!",Toast.LENGTH_SHORT).show();
                return;
            }
            FirebaseUtil.AdaptQuest(quest);
            FirebaseUtil.sendMessage(FirebaseUtil.loginUsername,quest.getPosterName(),"親愛的 "+quest.getPosterName()+" 您好,您的 "+quest.getQuestName()+" 任務已被 "+FirebaseUtil.loginUsername+" 接取!");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        binding.ChooseMap.setOnClickListener(view ->{
            Intent intent = new Intent(this,ChooseLocationInTakenActivity.class);
//            intent.putExtra("Lat", Taker_Lat);
//            intent.putExtra("Lon",Taker_Lon);
            startActivity(intent);
        });
    }
}