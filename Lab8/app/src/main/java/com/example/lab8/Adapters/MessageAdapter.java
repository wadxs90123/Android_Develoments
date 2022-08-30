package com.example.lab8.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8.CheckMap;
import com.example.lab8.FirebaseUtil;
import com.example.lab8.MainActivity;
import com.example.lab8.MessageActivity;
import com.example.lab8.PosterInQuestActivity;
import com.example.lab8.R;
import com.example.lab8.databinding.MessageSenderBinding;
import com.example.lab8.databinding.ReceivedMessageBinding;
import com.example.lab8.models.Message;
import com.example.lab8.models.Quest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<Message> mData = new ArrayList<>();

        public static final int VIEW_TYPE_SENT=1;
        public static final int VIEW_TYPE_RECEIVE=2;


        public MessageAdapter(ArrayList<Message> data) {
            mData = data;
        }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType == VIEW_TYPE_SENT){
                return new SentMessageViewHolder(
                        MessageSenderBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                        )
                );
            }else{
                return new ReceiveMessageViewHolder(
                        ReceivedMessageBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,
                                false
                        )
                );
            }
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position).getSender().equals(FirebaseUtil.loginUsername)){
            return VIEW_TYPE_SENT;
        }else{
            return VIEW_TYPE_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==VIEW_TYPE_SENT){
            try {
                ((SentMessageViewHolder)holder).setData(mData.get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                ((ReceiveMessageViewHolder)holder).setData(mData.get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
            private final MessageSenderBinding binding;
            public SentMessageViewHolder(MessageSenderBinding _binding) {
                super(_binding.getRoot());
                binding = _binding;
            }
            void setData(Message message) throws IOException {
                if(message.getMsg().equals("DefaultImage")){
                    binding.defaultImage.setVisibility(View.VISIBLE);
                    binding.defaultImage.setImageResource(R.drawable.street);
//                    MessageActivity.sendImage(binding.defaultImage);
                    binding.textDataTime.setPadding(0,320,0,0);
                    binding.textMessage.setHeight(binding.defaultImage.getHeight());
                }else{//sender == login , receiver == other
                    if(FirebaseUtil.hasQuest(message.getReceiver(),message.getSender())){
                        Quest quest = FirebaseUtil.findQuest( message.getReceiver(),message.getSender());
                        if(quest==null){
                            Log.d("MYTAG", "setData: h2");
                            binding.CompleteButton.setEnabled(false);
                            binding.CompleteButton.setText("已完成");
                        }else{
                            if(message.getMsg().length()>5&&message.getMsg().substring(0,3).equals("親愛的")){
                                //binding.textMessage.setWidth(100);
                                binding.CompleteButton.setVisibility(View.VISIBLE);
                                binding.CompleteButton.setOnClickListener(view->{
                                    view.setEnabled(false);
                                    binding.CompleteButton.setText("已完成");
                                    FirebaseUtil.deleteQuest(quest);
                                    FirebaseUtil.deleteMessages(message.getReceiver(),message.getSender());
                                    FirebaseUtil.addPoint(message.getSender(),5);
                                    Toast.makeText(view.getContext(),"恭喜!已完成此任務,獲得 5 點回饋點數", Toast.LENGTH_SHORT).show();
                                });
                            }else{
                                binding.CompleteButton.setVisibility(View.GONE);
                            }
                        }

                    }
                    binding.textMessage.setText(message.getMsg());
                }
                binding.textDataTime.setText(message.getTimestamp());
            }
        }
        static class ReceiveMessageViewHolder extends RecyclerView.ViewHolder{
            private final ReceivedMessageBinding binding;
            ReceiveMessageViewHolder(ReceivedMessageBinding _binding) {
                super(_binding.getRoot());
                binding = _binding;
            }
            void setData(Message message) throws IOException {
                if(message.getMsg().equals("DefaultImage")){
                    binding.defaultImage.setVisibility(View.VISIBLE);
                    binding.defaultImage.setImageResource(R.drawable.street);
                    binding.textDataTime.setPadding(0,320,0,0);
//                    MessageActivity.sendImage(binding.defaultImage);
                    binding.textMessage.setHeight(binding.defaultImage.getHeight());
                }else{
                    Log.d("MYTAG", "setData: hi");

                    if(FirebaseUtil.hasQuest(message.getSender(),message.getReceiver())){
                        Quest quest = FirebaseUtil.findQuest( message.getReceiver(),message.getSender());
                        if(quest==null){
                            Log.d("MYTAG", "setData: h2");
                            binding.CheckMapButton.setEnabled(false);
                            binding.CheckMapButton.setText("已完成");
                        }else{
                            if(message.getMsg().length()>5&&message.getMsg().substring(0,3).equals("親愛的")){
                                //binding.textMessage.setWidth(100);
                                binding.CheckMapButton.setVisibility(View.VISIBLE);
                                binding.CheckMapButton.setOnClickListener(view->{
                                    Intent intent = new Intent(view.getContext(), CheckMap.class);
                                    intent.putExtra("Name",quest.getReceiverName());
                                    view.getContext().startActivity(intent);
                                });
                            }else{
                                binding.CheckMapButton.setVisibility(View.GONE);
                            }
                        }

                    }


                    binding.textMessage.setText(message.getMsg());
                }
                binding.textDataTime.setText(message.getTimestamp());
                //                binding.textMessage.setText(message.getMsg());
//                binding.textDataTime.setText(message.getTimestamp());
            }
        }

//
//        // 建立ViewHolder
//        class ViewHolderSender extends RecyclerView.ViewHolder{
//            // 宣告元件
//            private CardView cardView;
//            private TextView QuestName;
//            private TextView QuestPoster;
//            private TextView PayoffAndLocation;
//            private TextView Date;
//            private TextView Time;
//
//            ViewHolderSender(View itemView) {
//                super(itemView);
//                cardView = itemView.findViewById(R.id.cardView);
//                QuestName = itemView.findViewById(R.id.QuestName);
//                QuestPoster = itemView.findViewById(R.id.QuestPoster);
//                PayoffAndLocation = itemView.findViewById(R.id.PayOffAndLocation);
//                Date = itemView.findViewById(R.id.Date);
//                Time = itemView.findViewById(R.id.Time);
//            }
//
//
//        }
//        class ViewHolderReceiver extends RecyclerView.ViewHolder{
//            // 宣告元件
//            private CardView cardView;
//            private TextView QuestName;
//            private TextView QuestPoster;
//            private TextView PayoffAndLocation;
//            private TextView Date;
//            private TextView Time;
//
//            ViewHolderReceiver(View itemView) {
//                super(itemView);
//                cardView = itemView.findViewById(R.id.cardView);
//                QuestName = itemView.findViewById(R.id.QuestName);
//                QuestPoster = itemView.findViewById(R.id.QuestPoster);
//                PayoffAndLocation = itemView.findViewById(R.id.PayOffAndLocation);
//                Date = itemView.findViewById(R.id.Date);
//                Time = itemView.findViewById(R.id.Time);
//            }
//
//
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
//        }
//
//    @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            // 連結項目布局檔list_item
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.recycler_view_row, parent, false);
//            return new RecyclerView.ViewHolder(view);
//        }
//        @SuppressLint("SetTextI18n")
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            // 設置txtItem要顯示的內容
//            holder.QuestName.setText(mData.get(position).getQuestName());
//            holder.QuestPoster.setText(mData.get(position).getPosterName());
//            holder.Date.setText(mData.get(position).getDate());
//            holder.PayoffAndLocation.setText(("單次$"+mData.get(position).getPayOff()+" "+mData.get(position).getLocation()));
//            holder.Time.setText(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()-mData.get(position).getCurrentTime())+"分鐘前");
//            holder.itemView.setOnClickListener(view -> {
//                Log.d("get", "onBindViewHolder: "+position);
//                Intent intent = new Intent(this.activity, PosterInQuestActivity.class);
//                intent.putExtra("Position",position);
//                MainActivity.activity.startActivity(intent);
//            });
////            if(mData.get(position).isTaken()){
////                holder.itemView.setVisibility(View.INVISIBLE);
////            }else{
////                holder.itemView.setVisibility(View.VISIBLE);
////            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return mData.size();
//        }
}
