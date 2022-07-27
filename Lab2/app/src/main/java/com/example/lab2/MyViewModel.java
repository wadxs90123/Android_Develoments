package com.example.lab2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> count;


    public MutableLiveData<Integer> getCount() {
        if(count == null){
            count = new MutableLiveData<>();
            count.setValue(0);
        }
        return count;
    }

    public void add(){
        count.setValue(count.getValue()+1);
    }
}
