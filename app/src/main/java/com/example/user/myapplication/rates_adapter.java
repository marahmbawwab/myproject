package com.example.user.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.annotation.NonNull;

import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;
import java.util.List;

public class rates_adapter extends RecyclerView.Adapter<rates_adapter.viewholder> {

    private List<rates_cardsview> cards;
    private Context c ;

    public rates_adapter(List<rates_cardsview> cards, Context c) {
        this.cards = cards;
        this.c = c;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_card,parent,false);
        return new viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        rates_cardsview list = cards.get(position);
        holder.username.setText("User name:"+list.getNames());
        holder.salonname.setText(list.getDes());
        holder.msg.setText("\n Review: "+list.getMsg());
        holder.s.setSelectedSmile(Integer.parseInt(list.getRate())-1);
        holder.s.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        holder.s.setFocusable(false);
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

    public void set_filter(List<rates_cardsview> item){

        cards= new ArrayList<>();
        cards.addAll(item);
        notifyDataSetChanged();



    }

    public class viewholder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView salonname;
        public TextView msg;
        public SmileRating s;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            username= (TextView) itemView.findViewById(R.id.username);
            salonname= (TextView) itemView.findViewById(R.id.salonname);
            msg= (TextView) itemView.findViewById(R.id.msg);
            s= (SmileRating) itemView.findViewById(R.id.smiley_rating);


        }
    }
}
