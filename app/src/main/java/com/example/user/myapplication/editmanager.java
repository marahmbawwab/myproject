package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class editmanager extends AppCompatActivity  {
    String usern, saloninfo, salonpassward, salonphone, emailaddress, address;
    Button edit ;
    EditText username, salonaddress, phone, email, passward;
    LinedEditText feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmanager);
        username = (EditText) findViewById(R.id.editText15);
        salonaddress = (EditText) findViewById(R.id.editText12);
        email = (EditText) findViewById(R.id.editText10);
        phone = (EditText) findViewById(R.id.editText14);
        passward = (EditText) findViewById(R.id.editText9);
        edit = (Button) findViewById(R.id.button5);
        feedback = (LinedEditText) findViewById(R.id.linedEditText2);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username .setText(spreferences.getString("user", null));
        usern=spreferences.getString("user", null);
        username.setEnabled(false);
        try {
            show(usern);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editinform(v);
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
    public void editinform(View view) throws ExecutionException, InterruptedException {
        usern = username.getText().toString();
        address = salonaddress.getText().toString();
        emailaddress = email.getText().toString();
        salonphone = phone.getText().toString();
        salonpassward = passward.getText().toString();
        saloninfo = feedback.getText().toString();
        show(usern);
        boolean notempty =true ;
        String type = "edit";
        if ( (usern.equals("")) || (address.equals("")) || (emailaddress.equals("")) || (salonphone.equals("")) || (salonpassward.equals(""))) {
            AlertDialog.Builder alert;
            notempty =false ;
            alert = new AlertDialog.Builder(editmanager.this);
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
            String myvalue = back.execute(type,  usern, address, emailaddress, salonphone, salonpassward, saloninfo).get();
            Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            if (!myvalue.contains("you are not registerd")){
                Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
                  Intent nextScreen = new Intent(getApplicationContext(), thirdmanager.class);
                  startActivity(nextScreen);
            }
            else {
                Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void show (String user) throws ExecutionException, InterruptedException {
     backgroundworker back = new backgroundworker(this);
          String  myvalue =back.execute("show",  user, address, emailaddress, salonphone, salonpassward, saloninfo).get();
        String[] n =myvalue.split("\n");
        List<String> list = Arrays.asList(n);
        if(list.contains("you")&& list.contains("are")&&list.contains("not")&&list.contains("reg")){
            Toast.makeText(this,"you are not registerd", Toast.LENGTH_SHORT).show();
            passward.setText("");
            salonaddress.setText("");
            phone.setText("");
            email.setText("");
            feedback.setText("");
        }
        else {
            passward.setText(n[1]);
            salonaddress.setText(n[2]);
            phone.setText(n[3]);
            email.setText(n[4]);
            feedback.setText(n[5]);
        }
    }
}
