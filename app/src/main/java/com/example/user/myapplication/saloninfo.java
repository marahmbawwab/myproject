package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class saloninfo extends AppCompatActivity {
    Spinner salonname ;
    EditText salone , address ,phone ;
    LinedEditText   mulline ;
    Button view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloninfo);
        salonname=(Spinner)findViewById(R.id.salon);
        view =(Button)findViewById(R.id.button9);
        address=(EditText)findViewById(R.id.editText);
        address.setEnabled(false);
        address.setKeyListener(null);
         salone=(EditText)findViewById(R.id.editText3);
         salone.setEnabled(false);
         phone=(EditText)findViewById(R.id.editText6);
         phone.setEnabled(false);
         salone.setKeyListener(null);
        mulline=(LinedEditText )findViewById(R.id.linedEditText2);
        mulline.setKeyListener(null);
        mulline.setEnabled(false);
        String[] items = new String[9] ;
        appointbackground back = new appointbackground(this);
        try {
            String  myvalue = back.execute("getsalonname",salone.getText().toString()).get();
            String[] n =myvalue.split("bb") ;
            int len =n.length ;
            items = new String[len+1] ;
            items[0]="Select a salon name";
            for (int i=1 ;i <items.length ; i++){
                items[i]=n[i-1];
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salonname.setAdapter(adapter);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    viewinform(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void   viewinform(View v) throws ExecutionException, InterruptedException {
       int index = salonname.getSelectedItemPosition();
        boolean notempty =true ;
        String type = "showsalon";
        if (index==0 ) {
            AlertDialog.Builder alert;
            notempty =false ;
            alert = new AlertDialog.Builder(saloninfo.this);
            alert.setMessage("Please select the salonname");
            alert.setTitle("Error message");
            alert.show();
        }
        else {
            String salonn = (String) salonname.getSelectedItem();
            saloninfoback back = new saloninfoback(this);
            String myvalue = back.execute(type,salonn).get();
            String [] newinfo = myvalue.split("\n");
            address.setText(newinfo[1]);
            phone.setText(newinfo[2]);
            salone.setText(newinfo[3]);
            mulline.setText(newinfo[4]);
       //     Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
        }
    }
}
