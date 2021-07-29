package com.example.user.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    String s1[] ,s2 [] ,s3[],s4[] ;
    Context con ;
    public adapter (Context ct , String  user [] ,String[] salons ,String [] dates,String times [] ){
      con = ct ;
      s1 = user ;
      s2 = salons ;
      s3 = dates ;
      s4 = times ;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.listlayout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       myViewHolder.user.setText(s1[i]);
        myViewHolder.salon.setText(s2[i]);
        myViewHolder.date.setText(s3[i]);
        myViewHolder.time.setText(s4[i]);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user , date , time , salon ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user =itemView.findViewById(R.id.username);
            date =itemView.findViewById(R.id.date);
            time =itemView.findViewById(R.id.time);
            salon =itemView.findViewById(R.id.salonname);
        }
    }
}
