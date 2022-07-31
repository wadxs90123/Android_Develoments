package com.example.lab8.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.FirebaseUtil;
import com.example.lab8.MainActivity;
import com.example.lab8.MessageActivity;
import com.example.lab8.PosterInQuestActivity;
import com.example.lab8.R;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

        private ArrayList<Message> mData = new ArrayList<>();

        public CommentAdapter(ArrayList<Message> data) {
            mData = data;
        }

        // 建立ViewHolder
        static class ViewHolder extends RecyclerView.ViewHolder{
            // 宣告元件
            private TextView chatterName;
            ViewHolder(View itemView) {
                super(itemView);
                chatterName = itemView.findViewById(R.id.ChatterName);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 連結項目布局檔list_item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment_view_row, parent, false);
            return new ViewHolder(view);
        }
        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // 設置txtItem要顯示的內容
            if(mData.get(position).getSender().equals(FirebaseUtil.loginUsername)){
                    holder.chatterName.setText(mData.get(position).getReceiver());
                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.activity, MessageActivity.class);
                    intent.putExtra("Name",mData.get(position).getReceiver());
                    MainActivity.activity.startActivity(intent);
                });
            }else{
                    holder.chatterName.setText(mData.get(position).getSender());
                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.activity, MessageActivity.class);
                    intent.putExtra("Name",mData.get(position).getSender());
                    MainActivity.activity.startActivity(intent);
                });
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
}
