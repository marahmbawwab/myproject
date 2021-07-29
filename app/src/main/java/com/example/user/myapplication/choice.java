package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {
    Button viewone ;
    Button viewall ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        viewall =(Button)findViewById(R.id.button14);
        viewone =(Button)findViewById(R.id.button15);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent nextScreen = new Intent(getApplicationContext(), home.class);
                // Intent nextScreen = new Intent(getApplicationContext(), my_grid.class);
           Intent nextScreen = new Intent(getApplicationContext(), salon_main.class);
                startActivity(nextScreen);

            }
        });
        viewone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),saloninfo.class);
                startActivity(nextScreen);

            }
        });


    }
}
