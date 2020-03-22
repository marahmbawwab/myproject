package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;
public class timetable extends AppCompatActivity {
   // private ListView timetable ;
    String [] item ={"beauty","hair style salon"};
    String [] datea ={"29/12/2020","23/7/2020"};
    String [] timea ={"2:30","1:20"};
    String [] username ={"suha","samia"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
      /*  Intent intent = getIntent();
        String items = intent.getStringExtra("Name");
        String dateas= intent.getStringExtra("date");
        String timeas = intent.getStringExtra("tim");
        String usernames = intent.getStringExtra("user");
        timetable= (ListView) findViewById(R.id.list);
        customadapter whatever = new customadapter ();
       timetable.setAdapter(whatever);
    }
    class customadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.listlayout,null);
            TextView salonTextField = (TextView)convertView.findViewById(R.id.textView22);
            TextView dateTextField = (TextView) convertView.findViewById(R.id.textView25);
            TextView timeTextField = (TextView) convertView.findViewById(R.id.textView24);
            TextView nameTextField = (TextView) convertView.findViewById(R.id.textView26);
            salonTextField.setText(item[i]);
            dateTextField.setText(datea[i]);
            timeTextField.setText(timea[i]);
            nameTextField.setText(username[i]);
            return convertView;
        }*/
    }

}
