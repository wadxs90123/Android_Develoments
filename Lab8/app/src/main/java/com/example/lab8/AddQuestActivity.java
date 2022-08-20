package com.example.lab8;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import java.text.DecimalFormat;
import java.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lab8.databinding.ActivityAddQuestBinding;
import com.google.android.gms.maps.model.LatLng;

public class AddQuestActivity extends AppCompatActivity {
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("發布工作");

        activity = this;
        setContentView(R.layout.activity_add_quest);
        ActivityAddQuestBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_add_quest);

        binding.questTitleInput.setText(getIntent().getStringExtra("Title"));
        binding.questTitleInput2.setText(getIntent().getStringExtra("Content"));
//        binding.editTextNumber.setText(getIntent().getStringExtra("Money"));
        binding.timePicker1.setText(getIntent().getStringExtra("Date1"));
        binding.timePicker2.setText(getIntent().getStringExtra("Date2"));
        binding.timePicker.setText(getIntent().getStringExtra("Time"));

        double Lat = getIntent().getDoubleExtra("Lat",0);
        double Lon = getIntent().getDoubleExtra("Lon",0);
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        String Lat_s = decimalFormat.format(Lat);
        String Lon_s = decimalFormat.format(Lon);
        binding.textView17.setText("經緯度("+Lon_s+" , "+Lat_s+")");

        binding.UserName.setText(FirebaseUtil.loginUsername);
        binding.PickFromDateImage.setOnClickListener(view -> {
            datePicker(view,binding.timePicker1);
        });
        binding.PickToDateImage.setOnClickListener(view->{
            datePicker(view,binding.timePicker2);
        });
        binding.PickTimeImage.setOnClickListener(view->{
            timePicker(view,binding.timePicker);
        });
        binding.AddQuestBackButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        binding.chooseMap.setOnClickListener(view->{
            Intent intent = new Intent(this,ChooseLocationActivity.class);
            intent.putExtra("Lat", Lat);
            intent.putExtra("Lon",Lon);
            intent.putExtra("Title",binding.questTitleInput.getText().toString());
            intent.putExtra("Content",binding.questTitleInput2.getText().toString());
//            intent.putExtra("Money",binding.editTextNumber.getText().toString());
            intent.putExtra("Date1",binding.timePicker1.getText().toString());
            intent.putExtra("Date2",binding.timePicker2.getText().toString());
            intent.putExtra("Time",binding.timePicker.getText().toString());
            startActivity(intent);
        });
        binding.AddQuest.setOnClickListener(view -> {
            String Poster = binding.UserName.getText().toString();
            String QuestTitleInput = binding.questTitleInput.getText().toString();//標題
            String ContentInput = binding.questTitleInput2.getText().toString();//內容
            String LocationInput = binding.textView17.getText().toString();//地點
//            int ValueInput = Integer.valueOf(binding.editTextNumber.getText().toString());//酬勞
            String DateFrom = binding.timePicker1.getText().toString();
            String DateTo = binding.timePicker2.getText().toString();
            String time = binding.timePicker.getText().toString();
            if(Lat==0&&Lon==0){
                Toast.makeText(view.getContext(),"請選擇地點",Toast.LENGTH_SHORT).show();
                return;
            }
//            FirebaseUtil.addQuest(Poster,null,QuestTitleInput,ValueInput,ContentInput,LocationInput,(DateFrom+"-"+DateTo));
            FirebaseUtil.addQuest(Poster,null,QuestTitleInput,ContentInput,(DateFrom+"-"+DateTo),time,Lat, Lon,0,0);
            Intent intent = new Intent(activity,MainActivity.class);
            startActivity(intent);
        });
    }
    public void datePicker(View v,EditText applydate) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);      //取得現在的日期年月日
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String datetime = String.valueOf(year) + "-" + String.valueOf(month+1) + "-" + String.valueOf(day);
                applydate.setText(datetime);   //取得選定的日期指定給日期編輯框
            }
        }, year, month, day).show();
    }
    public void timePicker(View v,EditText applytime) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String datetime = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                applytime.setText(datetime);  //取得選定的時間指定給時間編輯框
            }
        }, hourOfDay, minute,true).show();
    }
}