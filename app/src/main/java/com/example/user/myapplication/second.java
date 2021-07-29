package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class second extends AppCompatActivity {
    Button customer ;
    Button manager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        customer =(Button)findViewById(R.id.button15);
        manager =(Button)findViewById(R.id.button14);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent nextScreen = new Intent(getApplicationContext(), home.class);
              // Intent nextScreen = new Intent(getApplicationContext(), my_grid.class);
                startActivity(nextScreen);

            }
        });
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), homemanger.class);
                startActivity(nextScreen);

            }
        });


    }
}
