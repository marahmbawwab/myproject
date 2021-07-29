package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.concurrent.ExecutionException;

public class contact extends AppCompatActivity {
    Button send;
    EditText edit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        send = (Button) findViewById(R.id.button9);
        edit = (EditText)findViewById(R.id.editText);
        Spinner spinner1 = (Spinner) findViewById(R.id.salon);
        String[] items = new String[9] ;
        appointbackground back = new appointbackground(this);
        try {
            String  myvalue = back.execute("getsalonname",edit.getText().toString()).get();
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
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), third.class);
                startActivity(nextScreen);
            }
        });
    }
}