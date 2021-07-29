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

public class salon_chat_adapter extends RecyclerView.Adapter<salon_chat_adapter.view_holder> {
    public Button sendbutton;
    public  String salonname;
    private List<chat_cardview_salon> cards;
    private Context c ;

    public salon_chat_adapter(List<chat_cardview_salon> cards, Context c,String salonname) {
        this.cards = cards;
        this.c = c;
        this.salonname= salonname;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_card,parent,false);
        return new view_holder(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final view_holder holder, int position) {
        final chat_cardview_salon list = cards.get(position);
        holder.username.setText(list.getUsername());
        holder.msg.setText(list.getMsg());
        holder.time.setText("sending time:"+list.getTime());


        DateTimeFormatter dtf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        }
        LocalDateTime now = LocalDateTime.now();
        //  System.out.println(dtf.format(now));
        final String time= dtf.format(now);


        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rep = holder.replay.getText().toString();
                send_replay_user send = new send_replay_user(list.getUsername(),salonname,list.getMsg(),rep,time);
                holder.replay.setText("");
                //holder.username.setText("hhhhhhh");
            }
        });

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

    public void set_filter(List<chat_cardview_salon> item){

        cards= new ArrayList<>();
        cards.addAll(item);
        notifyDataSetChanged();



    }

    public class view_holder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView msg;
        public TextView time;
        public Button send;
        public EditText replay;


        public view_holder(@NonNull View itemView) {
            super(itemView);

            username= (TextView) itemView.findViewById(R.id.username);
            msg= (TextView) itemView.findViewById(R.id.msg);
            time= (TextView) itemView.findViewById(R.id.time);
            send= (Button)itemView.findViewById(R.id.sendb);
            replay = (EditText) itemView.findViewById(R.id.replay);
            //sendbutton= send;


        }
    }
}
