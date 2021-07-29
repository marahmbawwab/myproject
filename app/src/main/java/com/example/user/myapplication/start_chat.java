package com.example.user.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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

public class start_chat extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> dataAdapter;
    String salonname;
    String username ;
    ImageButton send;
    Button seemsg;
    EditText msgtext;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chat);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username=spreferences.getString("usern", null);
        send = (ImageButton)findViewById(R.id.send);
        seemsg = (Button)findViewById(R.id.seemsg);
        seemsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),  user_msg_show.class);
                startActivity(nextScreen);

            }
        });

        msgtext= (EditText)findViewById(R.id.editText);
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

        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                DateTimeFormatter dtf = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                }
                LocalDateTime now = LocalDateTime.now();
                //  System.out.println(dtf.format(now));
                String time= dtf.format(now);
                String type = "senddata";
                String msg = msgtext.getText().toString();
                connection sendconn= new connection();

                try {
                    String result = sendconn.execute(type,salonname,username,time,msg).get();
                    Toast.makeText(start_chat.this, result, Toast.LENGTH_SHORT).show();
                    msgtext.setText("");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });




    }
    public void setSpinner() throws ExecutionException, InterruptedException {
        connection conn;
        String result;
        String type= "spinner";
        spinner = (Spinner) findViewById(R.id.spinner2);
        List<String> labels = new ArrayList<String>();
        conn= new connection();
        result = conn.execute(type).get();
        // Toast.makeText(start_chat.this, result, Toast.LENGTH_SHORT).show();
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
          //  String host = "http://192.168.1.16/spinner.php";
          //  String usermsg_host = "http://192.168.1.16/usermsg.php";
             String host = "http://192.168.1.114/spinner.php";
              String usermsg_host = "http://192.168.1.114/usermsg.php";
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

            else if (type.equals("senddata")) {
                String salonname,username,time,msg;
                salonname = strings[1];
                username=strings[2];
                time=strings[3];
                msg= strings[4];
                // int rateint = Integer.parseInt(rates);
                try {
                    URL url = new URL(usermsg_host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String post_data = URLEncoder.encode("salonname","UTF-8")+"="
                            + URLEncoder.encode(salonname,"UTF-8")+"&"
                            + URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(username,"UTF-8")+"&"
                            + URLEncoder.encode("time","UTF-8")+"="+ URLEncoder.encode(time,"UTF-8")+"&"
                            + URLEncoder.encode("msg","UTF-8")+"="+ URLEncoder.encode(msg,"UTF-8");


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
                            //Toast.makeText(start_chat.this, item, Toast.LENGTH_SHORT).show();
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
