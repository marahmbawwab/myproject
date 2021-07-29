package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class user_msg_show_adapter extends RecyclerView.Adapter<user_msg_show_adapter.view_holder> {
    public Button sendbutton;
    public  String username;
    private List<user_chat_cardview> cards;
    private Context c ;

    public user_msg_show_adapter(List<user_chat_cardview> cards, Context c,String username) {
        this.cards = cards;
        this.c = c;
        this.username= username;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msgs,parent,false);
        return new view_holder(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final view_holder holder, int position) {
        final user_chat_cardview list = cards.get(position);
        holder.username.setText("User name:"+list.getUsername());
        holder.umsg.setText("User message:\n"+list.getUmsg());
        holder.salonname.setText("Salon name:"+list.getSalonname());
        holder.replay.setText("Salon message:\n"+list.getReplay());
        holder.time.setText("salon sending time: "+list.getTime());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void set_filter(List<user_chat_cardview> item){

        cards= new ArrayList<>();
        cards.addAll(item);
        notifyDataSetChanged();



    }

    public class view_holder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView umsg;
        public TextView salonname;
        public TextView replay;
        public TextView time;


        public view_holder(@NonNull View itemView) {
            super(itemView);

            username= (TextView) itemView.findViewById(R.id.username);
            umsg= (TextView) itemView.findViewById(R.id.umsg);
            salonname= (TextView) itemView.findViewById(R.id.salonname);
            replay= (TextView)itemView.findViewById(R.id.replay);
            time = (TextView) itemView.findViewById(R.id.time);
            //sendbutton= send;


        }
    }
}
