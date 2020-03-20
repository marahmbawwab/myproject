package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class thirdmanager extends AppCompatActivity {
    Button userp, rate,  appoint, about, vision , contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ActionBar actionBar = getSupportActionBar();
       // actionBar.setTitle("main");
        setContentView(R.layout.activity_thirdmanager);
        userp = (Button) findViewById(R.id.button5);
        rate = (Button) findViewById(R.id.button8);
        appoint = (Button) findViewById(R.id.button3);
        about = (Button) findViewById(R.id.button13);
        vision = (Button) findViewById(R.id.button14);
        contact = (Button) findViewById(R.id.button6);
        userp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), signupmanager.class);
                startActivity(nextScreen);

            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), timetable.class);
                startActivity(nextScreen);

            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), rateus.class);
                startActivity(nextScreen);

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), contactmanager.class);
                startActivity(nextScreen);

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), aboutus.class);
                startActivity(nextScreen);

            }
        });
        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), visionus.class);
                startActivity(nextScreen);

            }
        });
    }
}
