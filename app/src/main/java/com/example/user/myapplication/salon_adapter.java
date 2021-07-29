package com.example.user.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class salon_adapter extends RecyclerView.Adapter<salon_adapter.view_holder> {

    private List<salonview> cards;
    private Context c ;

    public salon_adapter(List<salonview> cards, Context c) {
        this.cards = cards;
        this.c = c;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.salon_card,parent,false);
        return new view_holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        salonview list = cards.get(position);
        holder.salonname.setText(list.getNames());
        holder.address.setText("Address:\t"+list.getAddress());
        holder.msg.setText("\n About us:\n"+list.getInfo());

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

    public void set_filter(List<salonview> item){

        cards= new ArrayList<>();
        cards.addAll(item);
        notifyDataSetChanged();



    }

    public class view_holder extends RecyclerView.ViewHolder {
        public TextView address;
        public TextView salonname;
        public TextView msg;

        public view_holder(@NonNull View itemView) {
            super(itemView);

            salonname= (TextView) itemView.findViewById(R.id.salonname);
            address= (TextView) itemView.findViewById(R.id.address);
            msg= (TextView) itemView.findViewById(R.id.info);



        }
    }
}
