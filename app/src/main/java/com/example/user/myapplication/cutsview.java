package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class cutsview extends AppCompatActivity {
    String user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutsview);
        ImageButton oval = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton round = (ImageButton)findViewById(R.id.imageButton4);
        ImageButton oblong = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton heart = (ImageButton)findViewById(R.id.imageButton5);
        ImageButton square = (ImageButton)findViewById(R.id.imageButton1);
        Toast.makeText(this, "please press on the photo to choose your face shape ", Toast.LENGTH_LONG).show();
        oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    set("oval");
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    set("heart");
            }
        });
        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    set("round");

            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set("square");
            }
        });
        oblong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set("oblong");
            }
        });
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
     /*   String im =spreferences.getString("image", null);
        background b = new background(this);
        try {
            String val = b.execute(im).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
    public void set(String shape) {
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
        spreferencesEditor.putString("shape",shape);
        spreferencesEditor .apply();
       // Intent nextScreen = new Intent(getApplicationContext(), activity_grid_item.class);
        Intent nextScreen = new Intent(getApplicationContext(),cutspage.class);
        startActivity(nextScreen);
    }

}
