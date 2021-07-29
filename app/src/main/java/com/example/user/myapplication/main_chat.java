package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

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

public class main_chat extends AppCompatActivity {
    private RecyclerView rview;
    Button sendbutton;
    private salon_chat_adapter adapter;
    private List<chat_cardview_salon> cardslist;
    String salonname ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        rview = (RecyclerView) findViewById(R.id.chatrecycle);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        cardslist = new ArrayList<>();
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        salonname=spreferences.getString("user", null);
        getmsgs();
    }
    public void getmsgs(){
        connection conn= new connection();
        String type="getmsgs";

        try {
            String result = conn.execute(type).get();
            // Toast.makeText(show_rates.this, result, Toast.LENGTH_LONG).show();
            adapter = new salon_chat_adapter(cardslist, main_chat.this,salonname);
            rview.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class connection extends AsyncTask<String, String, String> {
        int i;
        String result ="";
        String types="";
        String line="";
        Context c;


        @Override
        protected String doInBackground(String... strings) {
            result = "";
            String type = strings[0];
            types=type;
           // String host = "http://192.168.1.16/salon_getmsgs.php";
            String host = "http://192.168.1.114/salon_getmsgs.php";
            if (type.equals("getmsgs")) {
                try {
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String post_data = URLEncoder.encode("salonname","UTF-8")+"="
                            + URLEncoder.encode(salonname,"UTF-8");


                    bufferwriter.write( post_data);
                    bufferwriter.flush();
                    bufferwriter.close();
                    outputstream.close();
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

                    //return result;

                } catch (Exception e) {
                    return new String("there is exception:" + e.getMessage());
                }
                return result;
            }
            return "sorry";
        }







        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {
            if (types.equals("getmsgs")) {
                try {


                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray js = jsonObject.getJSONArray("msgs");

                        for(  i =0;i<js.length();i++)
                        {

                            JSONObject rate = js.getJSONObject(i);
                            String username= rate.getString("username");
                            String msg= rate.getString("msg");
                            String time= rate.getString("time");
                            //  String salonn= rate.getString("salonname");

                            chat_cardview_salon cs = new chat_cardview_salon(username,msg,time);
                            cardslist.add(cs);

                        }

                        adapter = new salon_chat_adapter(cardslist, main_chat.this,salonname);
                        rview.setAdapter(adapter);

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {

            // super.onProgressUpdate(values);*/
        }
    }//connclass

}
