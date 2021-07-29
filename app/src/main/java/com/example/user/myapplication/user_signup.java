package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class user_signup extends AppCompatActivity {
    EditText edate ;
    Button create ;
    TextView back ;
    EditText pass,un,fn,ln,email,phone;
    DatePickerDialog.OnDateSetListener setListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        create= (Button) findViewById(R.id.button2);
        edate= findViewById(R.id.bd);
        back = (TextView) findViewById(R.id.textView6);
        un= (EditText)findViewById(R.id.un);
        fn= (EditText)findViewById(R.id.fn);
        ln= (EditText)findViewById(R.id.ln);
        pass= (EditText)findViewById(R.id.pass);
        email= (EditText)findViewById(R.id.email);
        phone= (EditText)findViewById(R.id.phone);

        Calendar calender =Calendar.getInstance();
        final int year = calender.get(calender.YEAR);
        final int month = calender.get(calender.MONTH);
        final int day = calender.get(calender.DAY_OF_MONTH);
        edate.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         DatePickerDialog datePickerDialog =new DatePickerDialog(user_signup.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                                             @Override
                                             public void onDateSet(DatePicker view, int year, int month, int day) {
                                                 month=month+1;
                                                 String date =day +"/"+month +"/"+year ;
                                                 edate.setText(date);
                                             }
                                         },year,month,day);
                                         datePickerDialog.show();

                                     }
                                 }
        );



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),  home.class);
                startActivity(nextScreen);

            }
        });
        create .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String type ="register";
                backgroundworker2  backg;
                backg = new backgroundworker2();
                try{
                    String myv = backg.execute(type, un.getText().toString(), pass.getText().toString(),fn.getText().toString(),ln.getText().toString(),email.getText().toString(),phone.getText().toString(),edate.getText().toString()).get();
                    //Toast.makeText(user_signup.this,myv+"hi",Toast.LENGTH_SHORT).show();
                    if(myv.contains("register successful")){
                        Toast.makeText(user_signup.this,myv,Toast.LENGTH_SHORT).show();
                        Intent nextScreen = new Intent(getApplicationContext(), home.class);
                        startActivity(nextScreen);

                    }
                    else {
                        Toast.makeText(user_signup.this,myv,Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });



    }
    public void registeru() throws Exception {
        String password,usern,firstn,lastn,emailaddress,phoneno,bdate;
        password=pass.getText().toString();
        usern=un.getText().toString();
        firstn=fn.getText().toString();
        lastn=ln.getText().toString();
        emailaddress=email.getText().toString();
        phoneno=phone.getText().toString();
        bdate=edate.getText().toString();
        String type ="register";
        backgroundworker2  backg;
        backg = new backgroundworker2();
        String myv = backg.execute(type, usern, password,firstn,lastn,emailaddress,phoneno,bdate).get();
        if(myv.contains("register successful")){
            Toast.makeText(this,myv,Toast.LENGTH_SHORT).show();
            Intent nextScreen = new Intent(getApplicationContext(), home.class);
            startActivity(nextScreen);

        }
        else {
            Toast.makeText(this,myv,Toast.LENGTH_SHORT).show();
        }


    }
}
