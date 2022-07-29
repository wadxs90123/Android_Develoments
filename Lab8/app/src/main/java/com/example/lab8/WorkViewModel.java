package com.example.lab8;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class WorkViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<ArrayList<Quest>> quests;

    public MutableLiveData<ArrayList<Quest>> getQuests() {
        if(quests == null){
            quests = new MutableLiveData<>();
        }
        return quests;
    }
}