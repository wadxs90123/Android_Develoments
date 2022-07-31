package com.example.lab8;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab8.Adapters.WorkAdapter;
import com.example.lab8.databinding.FragmentWorkBinding;
import com.example.lab8.models.Quest;

import java.util.ArrayList;

public class WorkFragment extends Fragment {

    private WorkViewModel mViewModel;
    static RecyclerView recycler_view;
    //ArrayList<Quest> mData = new ArrayList<>();


    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    public static boolean flag = false;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Data Binding
        mViewModel = new ViewModelProvider(this).get(WorkViewModel.class);
        FragmentWorkBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_work,container,false);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(getActivity());
        //Data Binding
        flag = true;
        //AddQuestButton
        binding.addQuestButton.setOnClickListener(view -> {
            Intent AddQuest = new Intent(getActivity(), AddQuestActivity.class);
            startActivity(AddQuest);
        });

        //
        recycler_view = (RecyclerView) binding.WorkRecycler;
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(recycler_view.getContext()));
        ArrayList<Quest> quests = new ArrayList<>();
        for(Quest q : FirebaseUtil.QuestStore){
            if(!q.isTaken()){
                quests.add(q);
            }
        }
        // 將資料交給adapter
        WorkAdapter workAdapter = new WorkAdapter(quests);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(workAdapter);        //String PosterName,String ReceiverName,String QuestName,int Payoff,String Content,String Location,String PayMethod,String Date
        // 準備資料，塞50個項目到ArrayList裡
//        for(int i = 0; i < 50; i++) {
//            mData.add(new Quest("陳先生","王先生","急件!幫我倒垃圾",100,"請幫我倒垃圾","高雄市前鎮區成功路二段39號","匯款","2022/07/27~2022/07/31"));
//        }
//        for(Quest q : FirebaseUtil.QuestStore){
//            mData.add(q);
//        }
        // 連結元件


        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        flag = false;
    }
    public static void setup(){
        if(!flag){return;}
        ArrayList<Quest> quests = new ArrayList<>();
        for(Quest q : FirebaseUtil.QuestStore){
            if(!q.isTaken()){
                quests.add(q);
            }
        }
        // 將資料交給adapter
        WorkAdapter workAdapter = new WorkAdapter(quests);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(workAdapter);
        // 設置格線
        // recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }
}