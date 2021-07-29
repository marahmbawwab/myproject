package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class signupmanager extends AppCompatActivity {
    Button create;
    EditText username, salonaddress, phone, email, passward;
    LinedEditText feedback;
    String  usern, saloninfo, salonpassward, salonphone, emailaddress, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupmanager);
        username = (EditText) findViewById(R.id.editText10);
        salonaddress = (EditText) findViewById(R.id.editText12);
        email = (EditText) findViewById(R.id.editText15);
        phone = (EditText) findViewById(R.id.editText14);
        passward = (EditText) findViewById(R.id.editText9);
        create = (Button) findViewById(R.id.button2);
        feedback = (LinedEditText) findViewById(R.id.linedEditText2);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createaccount(v); ;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    public void createaccount(View view) throws ExecutionException, InterruptedException {
        usern = username.getText().toString();
        address = salonaddress.getText().toString();
        emailaddress = email.getText().toString();
        salonphone = phone.getText().toString();
        salonpassward = passward.getText().toString();
        saloninfo = feedback.getText().toString();
        String type = "create";
        boolean notempty =true ;
        if ((saloninfo.equals(""))||(usern.equals("")) || (address.equals("")) || (emailaddress.equals("")) || (salonphone.equals("")) || (salonpassward.equals(""))) {
           notempty =false ;
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(signupmanager.this);
            alert.setMessage("Please fill all the fields");
            alert.setTitle("Error message");
            alert.show();
        }
       if (!isValidMail(emailaddress)) {
            email.setError("there are an error in email format");
            email.requestFocus();
        }
        if (!isValidMobile(salonphone)) {
            phone.setError("mobile number should consist of 10 digits");
            phone.requestFocus();
        }
        if (isValidMail(emailaddress)&&isValidMobile(salonphone)&&notempty) {
            backgroundworker back = new backgroundworker(this);
            //  back.execute(type, salonn, usern, address, emailaddress, salonphone, salonpassward, saloninfo);
            String myvalue ;
            myvalue = back.execute(type,  usern, address, emailaddress, salonphone, salonpassward, saloninfo).get();
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            if (myvalue.contains("you are registered successfully")){
                Intent nextScreen = new Intent(getApplicationContext(), homemanger.class);
                startActivity(nextScreen);
            }
            //  finish();
        }

    }
}