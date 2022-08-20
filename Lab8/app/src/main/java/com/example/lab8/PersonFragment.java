package com.example.lab8;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab8.databinding.FragmentPersonBinding;
import com.example.lab8.databinding.FragmentWorkBinding;
import com.example.lab8.models.User;

import java.text.DecimalFormat;

public class PersonFragment extends Fragment {

    private PersonViewModel mViewModel;

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Data Binding
        mViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        FragmentPersonBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_person,container,false);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(getActivity());
        //Data Binding
        User user = FirebaseUtil.getUser(FirebaseUtil.loginUsername);

        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        String Lat_s = decimalFormat.format(user.getLat());
        String Lon_s = decimalFormat.format(user.getLon());

        binding.textView23.setText("經緯度("+Lon_s+" , "+Lat_s+")");

        binding.textView13.setText(FirebaseUtil.loginUsername);
        binding.Points.setText(FirebaseUtil.getUser(FirebaseUtil.loginUsername).getPoint()+"");
        binding.button3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.activity,PostWorksActivity.class);
            startActivity(intent);
        });
        binding.button4.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.activity,TakenWorksActivity.class);
            startActivity(intent);
        });
        binding.button5.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.activity,LoginActivity.class);
            startActivity(intent);

            FirebaseUtil.logout(FirebaseUtil.loginUsername);

            Toast.makeText(MainActivity.activity.getApplicationContext(),"登出成功!",Toast.LENGTH_SHORT).show();
        });
        binding.button10.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.activity,ChooseLocationInTakenActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }

}