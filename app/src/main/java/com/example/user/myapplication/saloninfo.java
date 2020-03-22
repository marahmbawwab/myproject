package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class saloninfo extends AppCompatActivity {
    Spinner salonname ;
    EditText salone , address ;
    LinedEditText   mulline ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloninfo);
        salonname=(Spinner)findViewById(R.id.salon);
        address=(EditText)findViewById(R.id.editText);
        address.setKeyListener(null);
         salone=(EditText)findViewById(R.id.editText3);
         salone.setKeyListener(null);
        mulline=(LinedEditText )findViewById(R.id.linedEditText2);
        mulline.setKeyListener(null);
        String[] items = new String[] {"Select a salon name","Beauty","hairstyle"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salonname.setAdapter(adapter);
    }
}
