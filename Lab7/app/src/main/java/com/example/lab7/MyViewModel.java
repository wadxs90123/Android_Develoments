package com.example.lab7;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber(){
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }
    public void add(int x){
        getNumber().setValue(getNumber().getValue()+x);
        if(getNumber().getValue()<0){
            getNumber().setValue(0);
        }
    }
}
