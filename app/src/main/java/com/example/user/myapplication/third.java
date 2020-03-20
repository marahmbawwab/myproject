package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.TooltipCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tooltip.Tooltip;

public class third extends AppCompatActivity {

    Button userp, feedinfo, nail, hair, appoint, contact ,about ,vision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        userp = (Button) findViewById(R.id.button5);
        feedinfo = (Button) findViewById(R.id.button8);
        nail = (Button) findViewById(R.id.button6);
        hair = (Button) findViewById(R.id.button7);
        appoint = (Button) findViewById(R.id.button9);
        contact = (Button) findViewById(R.id.button3);
       about = (Button) findViewById(R.id.button13);
        vision = (Button) findViewById(R.id.button14);
        userp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),  account.class);
                startActivity(nextScreen);

            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), appointpage.class);
                startActivity(nextScreen);

            }
        });
        feedinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), rateus.class);
                startActivity(nextScreen);

            }
        });
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),  choosehair.class);
                startActivity(nextScreen);

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),  about.class);
                startActivity(nextScreen);

            }
        });
        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), vision.class);
                startActivity(nextScreen);

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),saloninfo.class);
                startActivity(nextScreen);

            }
        });
    }
}

