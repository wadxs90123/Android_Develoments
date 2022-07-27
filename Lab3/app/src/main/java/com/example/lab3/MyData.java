package com.example.lab3;

import android.content.Context;
import android.content.SharedPreferences;

public class MyData {
    private final String Pre_Name;
    private final String Key_Name;
    private final int defInt;
    private final SharedPreferences shp;
    private final SharedPreferences.Editor editor;
    public int number;

    public MyData(Context context){
        defInt = context.getResources().getInteger(R.integer.DefaultValue);
        Pre_Name = context.getResources().getString(R.string.MY_DATA);
        Key_Name = context.getResources().getString(R.string.MY_KEY);
        shp = context.getSharedPreferences(Pre_Name,Context.MODE_PRIVATE);
        editor = shp.edit();
    }

    public void save() {
        editor.putInt(Key_Name, number);
        editor.apply();
    }

    public int load(){
        int x=  shp.getInt(Key_Name,defInt);
        number = x;
        return x;
    }
}
