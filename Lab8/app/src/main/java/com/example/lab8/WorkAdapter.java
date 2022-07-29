package com.example.lab8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder>{

        private Fragment fragment;
        private ArrayList<Quest> mData = new ArrayList<>();

        WorkAdapter(ArrayList<Quest> data,Fragment fragment) {
            mData = data;
            this.fragment = fragment;
        }

        // 建立ViewHolder
        class ViewHolder extends RecyclerView.ViewHolder{
            // 宣告元件
            private CardView cardView;
            private TextView QuestName;
            private TextView QuestPoster;
            private TextView PayoffAndLocation;
            private TextView Date;
            private TextView Time;

            ViewHolder(View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.cardView);
                QuestName = itemView.findViewById(R.id.QuestName);
                QuestPoster = itemView.findViewById(R.id.QuestPoster);
                PayoffAndLocation = itemView.findViewById(R.id.PayOffAndLocation);
                Date = itemView.findViewById(R.id.Date);
                Time = itemView.findViewById(R.id.Time);
            }


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 連結項目布局檔list_item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_row, parent, false);
            return new ViewHolder(view);
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // 設置txtItem要顯示的內容
            holder.QuestName.setText(mData.get(position).getQuestName());
            holder.QuestPoster.setText(mData.get(position).getPosterName());
            holder.Date.setText(mData.get(position).getDate());
            holder.PayoffAndLocation.setText(("單次$"+mData.get(position).getPayOff()+" "+mData.get(position).getLocation()));
            holder.Time.setText(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-mData.get(position).getCurrentTime())+"分鐘前");
            holder.itemView.setOnClickListener(view -> {
                Log.d("get", "onBindViewHolder: "+position);

                NavHostFragment navFragment = (NavHostFragment) fragment.getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);//找到navcontroller
                NavController controller = navFragment.getNavController();

                BottomNavigationView bottomNavigationView = fragment.getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.getMenu().getItem(0).setVisible(false);
                bottomNavigationView.getMenu().getItem(1).setVisible(false);
                bottomNavigationView.getMenu().getItem(2).setVisible(false);

                controller.navigate(R.id.action_workFragment_to_inQuestFragment);
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
}
