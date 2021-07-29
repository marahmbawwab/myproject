package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class account extends AppCompatActivity {
     EditText edate ;
     Button create  ;
     EditText fname ,lname,pass,phone,email,username ;
     DatePickerDialog.OnDateSetListener setListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
      create= (Button) findViewById(R.id.button2);
      edate= findViewById(R.id.editText17);
       username=findViewById(R.id.editText10);
       email= findViewById(R.id.editText12);
        phone=findViewById(R.id.editText14);
        pass=findViewById(R.id.editText9);
         fname=findViewById(R.id.editText8);
         lname=findViewById(R.id.editText15);
        create .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createnew(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                        String date =day +"-"+month +"-"+year ;
                        edate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }
    private boolean isValidMail(String email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            if (!Pattern.matches("[a-zA-Z]+", phone)) {
                return phone.length() == 10;
            }
            return false;
        }
        return false;
    }
    public void createnew(View v) throws ExecutionException, InterruptedException {
     String   usern = username.getText().toString();
        String emailaddress = email.getText().toString();
        String  uphone = phone.getText().toString();
        String  passward = pass.getText().toString();
        String  birth = edate.getText().toString();
        String fuser = fname.getText().toString();
        String luser = lname.getText().toString();
        String type = "addcustomer";
        boolean notempty =true ;
        if ((usern.equals("")) || (uphone.equals("")) || (emailaddress.equals("")) || (birth.equals("")) || (passward.equals(""))||(fuser.equals(""))||(luser.equals(""))) {
            notempty =false ;
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(account.this);
            alert.setMessage("Please fill all the fields");
            alert.setTitle("Error message");
            alert.show();
        }
        if (!isValidMail(emailaddress)) {
            email.setError("there are an error in email format");
            email.requestFocus();
        }
        if (!isValidMobile(uphone)) {
            phone.setError("mobile number should consist of 10 digits");
            phone.requestFocus();
        }
        if (isValidMail(emailaddress)&&isValidMobile(uphone)&&notempty) {
            customerhomebackground back = new customerhomebackground(this);
            String myvalue ;
            myvalue = back.execute(type, usern,passward, fuser,luser, emailaddress, uphone, birth).get();
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            if (myvalue.contains("you are registered successfully")){
                Intent nextScreen = new Intent(getApplicationContext(), home.class);
                startActivity(nextScreen);
            }
        }
    }

}
