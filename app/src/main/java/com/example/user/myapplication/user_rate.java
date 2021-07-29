package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.hsalf.smilerating.SmileRating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class user_rate extends AppCompatActivity {
    int selectedrate;
    Button sendb;
    EditText msg;
    Spinner spinner;
    String message;
    String salonname;
    String username ;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
       username=spreferences.getString("usern", null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rate);
        sendb = (Button) findViewById(R.id.send);
        msg = (EditText) findViewById(R.id.msgg);
        SmileRating smilerating = (SmileRating) findViewById(R.id.smiley_rating);
        try {
            setSpinner();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                salonname=spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        smilerating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {

                switch (smiley) {
                    case SmileRating.BAD:
                        Toast.makeText(user_rate.this, "BAD", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(user_rate.this, "GOOD", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(user_rate.this, "GREAT", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(user_rate.this, "OKAY", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(user_rate.this, "TERRIBLE", Toast.LENGTH_SHORT).show();
                        break;


                }
            }
        });

        smilerating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                selectedrate = level;
                Toast.makeText(user_rate.this, "Selected" + level, Toast.LENGTH_SHORT).show();
            }
        });


        sendb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection conn;
                String result;
                String type= "rate";
                conn= new connection();
                message= msg.getText().toString();
                try {
                    result = conn.execute(type,salonname, Integer.toString(selectedrate),message,username).get();
                    Intent nextScreen = new Intent(getApplicationContext(), basic_rate.class);
                    startActivity(nextScreen);
                    //Toast.makeText(user_rate.this, result, Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }//oncreate


    public void setSpinner() throws ExecutionException, InterruptedException {
        connection conn;
        String result;
        String type= "spinner";
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> labels = new ArrayList<String>();
        conn= new connection();
        result = conn.execute(type).get();
        //Toast.makeText(user_rate.this, result, Toast.LENGTH_SHORT).show();
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    class connection extends AsyncTask<String, String, String> {
        int i;
        String result ="";
        String types="";
        String line="";
        @Override
        protected String doInBackground(String... strings) {
            result = "";
            String type = strings[0];
            types=type;
            String host = "http://192.168.1.114/spinner.php";
            String rate_host = "http://192.168.1.114/rate.php";
            if (type.equals("spinner")) {
                try {
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    InputStream inputstream = connection.getInputStream();
                    BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));
                    StringBuffer buffer = new StringBuffer("");
                    line = "";
                    while ((line = bufferreader.readLine()) != null) {
                        buffer.append(line+"\n");
                        break;
                    }
                    bufferreader.close();
                    result = buffer.toString();


                } catch (Exception e) {
                    return new String("there is exception:" + e.getMessage());
                }
                return result;
            }
            else if (type.equals("rate")) {
                String sname,rates,msgs,uname;
                sname = strings[1];
                rates=strings[2];
                msgs=strings[3];
                uname= strings[4];
                // int rateint = Integer.parseInt(rates);
                try {
                    URL url = new URL(rate_host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String post_data = URLEncoder.encode("salonname","UTF-8")+"="
                            + URLEncoder.encode(sname,"UTF-8")+"&"
                            + URLEncoder.encode("rate","UTF-8")+"="+ URLEncoder.encode(rates,"UTF-8")+"&"
                            + URLEncoder.encode("msg","UTF-8")+"="+ URLEncoder.encode(msgs,"UTF-8")+"&"
                            + URLEncoder.encode("uname","UTF-8")+"="+ URLEncoder.encode(uname,"UTF-8");

                    bufferwriter.write( post_data);
                    bufferwriter.flush();
                    bufferwriter.close();
                    outputstream.close();
                    InputStream inputstream = connection.getInputStream();
                    BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));

                    StringBuilder sb = new StringBuilder();
                    String json=  "";

                    while((json= bufferreader.readLine()) != null){
                        sb.append(json+"\n");
                    }
                    String sbstr= sb.toString().trim();
                    return sbstr;
                } catch (Exception e) {
                    return new String("there is exception:" + e.getMessage());
                }
            }
            return result;
        }
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(String result) {
            if (types.equals("spinner")) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray salons = jsonObject.getJSONArray("salons");
                        for (i = 0; i < salons.length(); i++) {
                            JSONObject salon = salons.getJSONObject(i);
                            String item = salon.getString("username");
                            dataAdapter.add(item);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {

            // super.onProgressUpdate(values);
        }
    }//connclass

}




