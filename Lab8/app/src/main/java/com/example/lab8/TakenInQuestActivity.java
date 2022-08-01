package com.example.lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lab8.databinding.ActivityTakenInQuestBinding;
import com.example.lab8.googlemap.MapsFragment;
import com.example.lab8.models.Quest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TakenInQuestActivity extends AppCompatActivity   {

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
        binding.inQuestDateText.setText(quest.getDate()+" "+quest.getTime());
//        binding.InQuestLocationText.setText(quest.getLocation());

        binding.InQuestBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,TakenWorksActivity.class);
            startActivity(intent);
        });
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
        //新增google map fragment
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,new MapsFragment(quest));
        fragmentTransaction.commit();

    }

}