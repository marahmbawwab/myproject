package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class discount extends AppCompatActivity {
    String username ,sname ;
    TextView name,ssname ,point ,discount ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        name =(TextView)findViewById(R.id.textView36);
       ssname  =(TextView)findViewById(R.id.textView37);
       point  =(TextView)findViewById(R.id.textView38);
       discount =(TextView)findViewById(R.id.textView39);
        Intent intent = getIntent();
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = spreferences.getString("usern", null);
        sname = intent.getStringExtra("salon");
        try {
            update();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void update() throws ExecutionException, InterruptedException {
     appointbackground back = new appointbackground(this);
     String myval = back.execute("getpoint",username,sname).get();
        Toast.makeText(this,myval,Toast.LENGTH_LONG).show();
    /* if(!myval.contains("done")) {
         name.setText(username);
         ssname.setText(sname);
         point.setText(myval);
         double dis = (Integer.parseInt(myval) * 20) / 10;
         String dd = Double.toString(dis);
         discount.setText(dd);
     }
     else {
         name.setText(username);
         ssname.setText(sname);
         point.setText(Integer.toString(20));
         double dis = 10;
         String dd = Double.toString(dis);
         discount.setText(dd);
     }*/
    }
}
