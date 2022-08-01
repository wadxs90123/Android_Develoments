package com.example.lab8.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.InQuestActivity;
import com.example.lab8.MainActivity;
import com.example.lab8.R;
import com.example.lab8.models.Quest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder>{

        private ArrayList<Quest> mData = new ArrayList<>();

        public WorkAdapter(ArrayList<Quest> data) {
            mData = data;
        }

        // 建立ViewHolder
        static class ViewHolder extends RecyclerView.ViewHolder{
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
                QuestName = itemView.findViewById(R.id.ChatterName);
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
            DecimalFormat decimalFormat = new DecimalFormat("###.##");
            String Lat_s = decimalFormat.format(mData.get(position).getLat());
            String Lon_s = decimalFormat.format(mData.get(position).getLon());
            holder.PayoffAndLocation.setText(("單次$"+mData.get(position).getPayOff()+" "+"經緯度("+Lon_s+" , "+Lat_s+")"));

            // 設置txtItem要顯示的內容
            holder.QuestName.setText(mData.get(position).getQuestName());
            holder.QuestPoster.setText(mData.get(position).getPosterName());
            holder.Date.setText(mData.get(position).getDate());
            holder.Time.setText(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-mData.get(position).getCurrentTime())+"分鐘前");
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.activity, InQuestActivity.class);
                intent.putExtra("ID", mData.get(position).getId());
                MainActivity.activity.startActivity(intent);
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
}
