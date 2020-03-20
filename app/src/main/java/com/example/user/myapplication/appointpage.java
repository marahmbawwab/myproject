package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.tooltip.OnClickListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class appointpage extends AppCompatActivity {
    final String [] chars = new String[] {"Currently reserved times"};
    EditText text ;
    Spinner serv ;
    String services ;
    TimePickerDialog picker ;
    CalendarView calender ;
    Button confirm ,cancel ;
    EditText nam ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apoint_act);
        String[] items = new String[] {"Select a salon name","Beauty","hairstyle"};
        confirm=(Button)findViewById(R.id.button12);
        calender =(CalendarView)findViewById(R.id.cal);
        cancel=(Button)findViewById(R.id.button13);
        text = (EditText)findViewById(R.id.editText4);
        serv= (Spinner)findViewById(R.id.salon);
        nam=(EditText)findViewById(R.id.name);
        Integer indexValue =serv.getSelectedItemPosition();
     /*   if(indexValue==0){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(appointpage.this);
            builder1.setMessage("please select a salon name ");
            builder1.setCancelable(true);
            builder1.setIcon(android.R.drawable.ic_dialog_alert);
            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else {
             services =serv.getSelectedItem().toString();
        }*/
      calender.setOnDateChangeListener( new CalendarView.OnDateChangeListener(){

           @Override
           public void onSelectedDayChange(CalendarView view, int year,
                                           int month, int dayOfMonth) {
               String date = dayOfMonth +" "+month +" "+year ;
               String item= services +"  " ;
               String tim = text.getText() +"  ";
               String name = nam.getText()+" ";
               Intent nextScreen = new Intent(appointpage.this, timetable.class);
               nextScreen.putExtra("Name",item);
               nextScreen.putExtra("date",date);
               nextScreen.putExtra("time",tim);
               nextScreen.putExtra("user",name);
               startActivity(nextScreen);

           }
       });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), third.class);
                startActivity(nextScreen);

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), third.class);
                startActivity(nextScreen);

            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                picker =new TimePickerDialog(appointpage.this, R.style.myDialog,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        text.setText(hourOfDay +":"+ minute);
                    }
                }, hour, minute, true);//Yes 24 hour tim
                picker.setTitle("Select Time");
                picker.show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serv.setAdapter(adapter);
        /*@Override
public boolean isEnabled(int position) {
    if (position == 0) {
        // Disable the first item from Spinner
        // First item will be use for hint
        return false;
    } else {
        return true;
    }
}*/


    }
}
