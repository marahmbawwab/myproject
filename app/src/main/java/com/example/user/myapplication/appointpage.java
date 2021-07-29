package com.example.user.myapplication;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class appointpage extends AppCompatActivity  {
    EditText text ;
    Spinner serv ;
    TimePickerDialog picker ;
    CalendarView calender ;
    Button confirm ,show ;
    EditText nam ;
    String date ,user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apoint_act);
        confirm=(Button)findViewById(R.id.button12);
        calender =(CalendarView)findViewById(R.id.cal);
        show=(Button)findViewById(R.id.button13);
        text = (EditText)findViewById(R.id.editText4);
        serv= (Spinner)findViewById(R.id.salon);
        nam=(EditText)findViewById(R.id.name);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
        nam .setText(spreferences.getString("usern", null));
        user=nam.getText().toString();
        nam.setEnabled(false);
        String[] items = new String[9] ;
        appointbackground back = new appointbackground(this);
        try {
            String  myvalue = back.execute("getsalonname",text.getText().toString()).get();
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
        serv.setAdapter(adapter);
      calender.setOnDateChangeListener( new CalendarView.OnDateChangeListener(){

           @Override
           public void onSelectedDayChange(CalendarView view, int year,
                                           int month, int dayOfMonth) {
                date = dayOfMonth+"-"+(month+1)+"-"+year ;
           }
       });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    appoint(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException | ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showapp (v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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

    }
    public  void appoint(View v) throws ExecutionException, InterruptedException, ParseException {
       String time = text.getText().toString();
        Integer indexValue =serv.getSelectedItemPosition();
        String salonn = (String) serv.getSelectedItem();
        boolean notempty =true ;
        String type = "makeappoint";
        if ((time.equals("")) || (indexValue==0) || (date==null)) {
            notempty = false ;
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(appointpage.this);
            alert.setMessage("Please fill all the fields");
            alert.setTitle("Error message");
            alert.show();
        }
        else if (notempty ) {
         appointbackground  back = new appointbackground(this);
         String myvalue ;
         myvalue= back.execute(type, user,salonn, date,time).get();
         Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
         if(myvalue.contains("your appointment done successfully")){
             Intent nextScreen = new Intent(getApplicationContext(), third.class);
         //    Intent nextScreen = new Intent(getApplicationContext(), discount.class);
             nextScreen.putExtra("salon", salonn);
             startActivity(nextScreen);
        }
        }
    }
    public  void showapp(View v) throws ExecutionException, InterruptedException, ParseException {
      String time = text.getText().toString();
        Integer indexValue =serv.getSelectedItemPosition();
        String salonn = (String) serv.getSelectedItem();
        boolean notempty =true ;
        String type = "showappoint";
        if ((indexValue==0) || (date== null)) {
            notempty = false ;
            AlertDialog.Builder alert;
            alert = new AlertDialog.Builder(appointpage.this);
            alert.setMessage("Please select the date and salonname ");
            alert.setTitle("Error message");
            alert.show();
        }
        else {
            appointbackground back = new appointbackground(this);
            String myvalue;
            myvalue = back.execute(type, user, salonn, date, time).get();
           // Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
           if (!myvalue.contains("there is no appointment to show in that day")) {
                Intent nextScreen = new Intent(appointpage.this, timetable.class);
               nextScreen.putExtra("data",myvalue);
                startActivity(nextScreen);
            }
            else {
                Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
            }
        }

    }

    }


