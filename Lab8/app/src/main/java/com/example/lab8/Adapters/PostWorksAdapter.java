package com.example.lab8.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.PosterInQuestActivity;
import com.example.lab8.R;
import com.example.lab8.models.Quest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PostWorksAdapter extends RecyclerView.Adapter<PostWorksAdapter.ViewHolder>{

        private Activity activity;
        private ArrayList<Quest> mData = new ArrayList<>();

        public PostWorksAdapter(ArrayList<Quest> data, Activity activity) {
            mData = data;
            this.activity = activity;
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
            // 設置txtItem要顯示的內容
            holder.QuestName.setText(mData.get(position).getQuestName());
            holder.QuestPoster.setText(mData.get(position).getPosterName());
            holder.Date.setText(mData.get(position).getDate());
            holder.PayoffAndLocation.setText(("單次$"+mData.get(position).getPayOff()+" "+mData.get(position).getLocation()));
            holder.Time.setText(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-mData.get(position).getCurrentTime())+"分鐘前");
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(activity, PosterInQuestActivity.class);
                intent.putExtra("ID",mData.get(position).getId());
                activity.startActivity(intent);
            });
//            if(mData.get(position).isTaken()){
//                holder.itemView.setVisibility(View.INVISIBLE);
//            }else{
//                holder.itemView.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
}
