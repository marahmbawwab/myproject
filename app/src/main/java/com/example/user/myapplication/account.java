package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class account extends AppCompatActivity {
     EditText edate ;
     Button create ;
    TextView back ;
     DatePickerDialog.OnDateSetListener setListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
      create= (Button) findViewById(R.id.button2);
      edate= findViewById(R.id.editText17);
        create .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), home.class);
                startActivity(nextScreen);

            }
        });
        Calendar calender =Calendar.getInstance();
        final int year = calender.get(calender.YEAR);
        final int month = calender.get(calender.MONTH);
        final int day = calender.get(calender.DAY_OF_MONTH);
        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(account.this, R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                       month=month+1;
                        String date =day +"/"+month +"/"+year ;
                        edate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

}
