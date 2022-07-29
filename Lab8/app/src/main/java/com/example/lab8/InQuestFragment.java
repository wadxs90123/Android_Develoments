package com.example.lab8;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab8.databinding.FragmentInQuestBinding;
import com.example.lab8.databinding.FragmentWorkBinding;

public class InQuestFragment extends Fragment {

    private InQuestViewModel mViewModel;

    public static InQuestFragment newInstance() {
        return new InQuestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Data Binding
        mViewModel = new ViewModelProvider(this).get(InQuestViewModel.class);
        FragmentInQuestBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_in_quest,container,false);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(getActivity());
        //Data Binding


        return binding.getRoot();
    }

}