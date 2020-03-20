package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homemanger extends AppCompatActivity {
    TextView signup ;
    TextView forgott;
    Button signin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemanger);
        signup=(TextView) findViewById(R.id.textView2) ;
        forgott=(TextView) findViewById(R.id.textView3) ;
        signin=(Button)findViewById(R.id.button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), signupmanager.class);
                startActivity(nextScreen);

            }
        });
        forgott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), forgotmanager.class);
                startActivity(nextScreen);

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), thirdmanager.class);
                startActivity(nextScreen);

            }
        });
    }
}
