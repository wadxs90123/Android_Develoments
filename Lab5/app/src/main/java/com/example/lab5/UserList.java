package com.example.lab5;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserList extends ArrayAdapter<ChatFormat> {

    private Activity context;
    private List<ChatFormat> chatList;

    public UserList(Activity context,List<ChatFormat> chatList) {
        super(context,R.layout.mission_layout,chatList);
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.mission_layout,null,true);

        TextView missionName = listViewItem.findViewById(R.id.EventName);

        TextView t1 = listViewItem.findViewById(R.id.t1);
        TextView t2 = listViewItem.findViewById(R.id.t2);
        TextView t3 = listViewItem.findViewById(R.id.t3);
        TextView t4 = listViewItem.findViewById(R.id.t4);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Message/"+t1.getText().toString());



        listViewItem.setOnClickListener(v->{
            ChatFormat ch = chatList.get(position);
            Intent intent = new Intent(context,GarbageActivity.class);
            //intent.putExtra("uuid",ch.getUuid());
            intent.putExtra("name",(ch.getEventName()));
            intent.putExtra("snd",(ch.getSnd_name()));
            intent.putExtra("payoff",(ch.getPayoff()));
            intent.putExtra("message",(ch.getMessage()));
            intent.putExtra("pos",(ch.getPos()));
            context.startActivity(intent);

        });

        ChatFormat ch = chatList.get(position);


        missionName.setText(ch.getEventName());
        t1.setText(ch.getSnd_name());
        t2.setText(ch.getPayoff());
        t3.setText(ch.getMessage());
        t4.setText(ch.getPos());


        return listViewItem;
    }
}
