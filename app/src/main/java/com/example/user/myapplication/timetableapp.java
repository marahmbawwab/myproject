package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class timetableapp extends AppCompatActivity {
    String  users []   ;
    String  namessalon []   ;
    String  times []   ;
    RecyclerView list ;
    String dates [] ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetableapp);
        String[] data =getIntent().getStringExtra("data").split("\n");
        String user[][]=new String[data.length][4];
        for (int i=0 ; i < data.length ; i++){
            user[i]= data[i].split("\t");
        }
        int j=0 ;
        users =new String[data.length];
        for (int i = 0; i < data.length; i++) {
            users[i] = user[i][j];
        }
        j=1 ;
        namessalon =new String[data.length];
        for (int i = 0; i < data.length; i++) {
            namessalon[i] = user[i][j];
        }
        j=2 ;
        dates =new String[data.length];
        for (int i = 0; i < data.length; i++) {
            dates[i] = user[i][j];
        }
        j=3 ;
        times =new String[data.length];
        for (int i = 0; i < data.length; i++) {
            times[i] = user[i][j];
        }
        list = findViewById(R.id.list);
        adapter myadapter = new adapter(this,users,namessalon,dates,times);
        list.setAdapter(myadapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }
}
