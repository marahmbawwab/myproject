package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class edituser extends AppCompatActivity {
    String usern, first,last, passward, phone, email,birth;
    Button edit ;
    EditText username,  phonen, emailu, pass,lastn,firstn,birthd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        username = (EditText) findViewById(R.id.editText8);
        emailu = (EditText) findViewById(R.id.editText12);
        phonen = (EditText) findViewById(R.id.editText14);
        pass = (EditText) findViewById(R.id.editText9);
        lastn = (EditText) findViewById(R.id.editText15);
        firstn= (EditText) findViewById(R.id.editText10);
        birthd = (EditText) findViewById(R.id.editText17);
        edit = (Button) findViewById(R.id.button1);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username .setText(spreferences.getString("usern", null));
        usern=spreferences.getString("usern", null);
        username.setEnabled(false);
        try {
            show ("showu",usern) ;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    edituser();
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
        birthd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(edituser.this, R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date =day +"-"+month +"-"+year ;
                       birthd.setText(date);
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
    public void show (String type ,String user ) throws ExecutionException, InterruptedException {
        customerhomebackground bb = new customerhomebackground(this);
        String byval = bb.execute(type,user).get();
       String [] data = byval.split("\n");
       emailu.setText(data[4]);
       phonen.setText(data[3]);
       birthd.setText(data[2]);
       pass.setText(data[1]);
       firstn.setText(data[6]);
       lastn.setText(data[5]);

    }
    public void edituser() throws ExecutionException, InterruptedException {
      //  usern = username.getText().toString();
        email = emailu.getText().toString();
        phone = phonen.getText().toString();
        passward = pass.getText().toString();
        birth = birthd.getText().toString();
        first=firstn.getText().toString();
        last=lastn.getText().toString();
        boolean notempty =true ;
        if ( (usern.equals("")) || (email.equals("")) || (phone.equals("")) || (passward.equals("")) || (birth.equals(""))||(first.equals(""))||(last.equals(""))) {
            AlertDialog.Builder alert;
            notempty =false ;
            alert = new AlertDialog.Builder(edituser.this);
            alert.setMessage("Please fill all the fields");
            alert.setTitle("Error message");
            alert.show();
        }
        if (!isValidMail(email)) {
            emailu.setError("there are an error in email format");
            emailu.requestFocus();
        }
        if (!isValidMobile(phone)) {
            phonen.setError("mobile number should consist of 10 digits");
            phonen.requestFocus();
        }
        if (isValidMail(email)&&isValidMobile(phone)&&notempty) {
            customerhomebackground back = new customerhomebackground(this);
            String myvalue = back.execute("edit",usern,passward,birth, email, phone, first, last).get();
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            if (!myvalue.contains("you are not registerd")){
                Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
                Intent nextScreen = new Intent(getApplicationContext(), third.class);
                startActivity(nextScreen);
            }
            else {
                Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
