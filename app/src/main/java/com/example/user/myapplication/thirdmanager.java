package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.concurrent.ExecutionException;

public class thirdmanager extends AppCompatActivity {
    Button userp, rate,  appoint, about, vision , contact;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdmanager);
        userp = (Button) findViewById(R.id.button5);
        rate = (Button) findViewById(R.id.button8);
        appoint = (Button) findViewById(R.id.button3);
        about = (Button) findViewById(R.id.button13);
        vision = (Button) findViewById(R.id.button14);
        contact = (Button) findViewById(R.id.button6);
       SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       name = spreferences.getString("user", null);
        userp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), editmanager.class);
                startActivity(nextScreen);

            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getappoint(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent nextScreen = new Intent(getApplicationContext(), salonrate.class);
              //  Intent nextScreen = new Intent(getApplicationContext(), show_rates.class);
                Intent nextScreen = new Intent(getApplicationContext(), managerrate.class);
                startActivity(nextScreen);

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent nextScreen = new Intent(getApplicationContext(), contactmanager.class);
                Intent nextScreen = new Intent(getApplicationContext(), main_chat.class);
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

    public void getappoint(View v) throws ExecutionException, InterruptedException {
        String type = "showsalonapp";
        saloninfoback back = new saloninfoback(this);
        String myvalue = back.execute(type, name).get();
        if (!myvalue.contains("there is no appointment to show ")) {
            Intent nextScreen = new Intent(getApplicationContext(), timetableapp.class);
            nextScreen.putExtra("data", myvalue);
            startActivity(nextScreen);
        }else {
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
        }
    }
}
