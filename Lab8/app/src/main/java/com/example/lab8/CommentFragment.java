package com.example.lab8;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab8.Adapters.CommentAdapter;
import com.example.lab8.Adapters.WorkAdapter;
import com.example.lab8.databinding.FragmentCommentBinding;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CommentFragment extends Fragment {

    private CommentViewModel mViewModel;
    static RecyclerView recycler_view;
    public static boolean flag = false;
    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Data Binding
        mViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        FragmentCommentBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_comment,container,false);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(getActivity());
        //Data Binding
        flag = true;

        recycler_view = (RecyclerView) binding.Chatter;
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(recycler_view.getContext()));
        //setup();

        ArrayList<Message> messages = new ArrayList<>();
        Set<String> Name = new HashSet<>();

        for(Message m : FirebaseUtil.MessageStore){
            if(m.getReceiver().equals(FirebaseUtil.loginUsername)){
                if(!Name.contains(m.getSender())){
                    messages.add(m);
                    Name.add(m.getSender());
                }
            }
            if(m.getSender().equals(FirebaseUtil.loginUsername)){
                if(!Name.contains(m.getReceiver())){
                    messages.add(m);
                    Name.add(m.getReceiver());
                }
            }
        }

        // 將資料交給adapter
        CommentAdapter commentAdapter = new CommentAdapter(messages);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(commentAdapter);
        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        flag = false;
    }

    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        flag = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        flag= false;
    }

    public static void setup(){
        if(!flag){return;}
        ArrayList<Message> messages = new ArrayList<>();
        Set<String> Name = new HashSet<>();

        for(Message m : FirebaseUtil.MessageStore){
            if(m.getReceiver().equals(FirebaseUtil.loginUsername)){
                if(!Name.contains(m.getSender())){
                    messages.add(m);
                    Name.add(m.getSender());
                }
            }
            if(m.getSender().equals(FirebaseUtil.loginUsername)){
                if(!Name.contains(m.getReceiver())){
                    messages.add(m);
                    Name.add(m.getReceiver());
                }
            }
        }

        // 將資料交給adapter
        CommentAdapter commentAdapter = new CommentAdapter(messages);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(commentAdapter);
        // 設置格線
        // recycler_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }
}