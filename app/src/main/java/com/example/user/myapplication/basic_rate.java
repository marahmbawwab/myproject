package com.example.user.myapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class basic_rate extends AppCompatActivity {
    Button userrates , seerates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_rate);
        userrates= (Button)findViewById(R.id.imageButton2);
        seerates= (Button)findViewById(R.id.button2);
        userrates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), user_rate.class);
                startActivity(nextScreen);

            }
        });
        seerates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent nextScreen = new Intent(getApplicationContext(), show_rates.class);
                startActivity(nextScreen);

            }
        });

    }
}
