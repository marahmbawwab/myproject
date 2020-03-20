package com.example.user.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class forgot extends AppCompatActivity {
    Button button ;
    Button confirm ;
    private ProgressBar progressbar;
    private ObjectAnimator proganim ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        button = (Button) findViewById(R.id.button4);
        confirm = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), home.class);
                startActivity(nextScreen);

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), home.class);
                startActivity(nextScreen);

            }
        });
        init();
        proganim.setDuration(7000);
        proganim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                progressbar.setVisibility(View.GONE);
            }
        });

    }
    public void init(){
        progressbar =(ProgressBar)findViewById(R.id.simpleProgressBar);
        proganim=ObjectAnimator.ofInt(progressbar,"progress", (int) .100);

    }
}
