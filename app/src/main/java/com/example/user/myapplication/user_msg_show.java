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

public class user_msg_show extends AppCompatActivity {
    private RecyclerView rview;
    Button sendbutton;
    private user_msg_show_adapter adapter;
    private List<user_chat_cardview> cardslist;
    String username ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg_show);
        rview = (RecyclerView) findViewById(R.id.userrecycle);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        cardslist = new ArrayList<>();
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username=spreferences.getString("usern", null);
        getmsgs();

    }
    public void getmsgs(){
        connection conn= new connection();
        String type="getmsgs";

        try {
            String result = conn.execute(type).get();
            // Toast.makeText(show_rates.this, result, Toast.LENGTH_LONG).show();
            adapter = new user_msg_show_adapter(cardslist, user_msg_show.this,username);
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
         //   String host = "http://192.168.1.16/user_getmsgs.php";
            String host = "http://192.168.1.114/user_getmsgs.php";
            if (type.equals("getmsgs")) {
                //Toast.makeText(show_rates.this, "get", Toast.LENGTH_SHORT).show();
                try {
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="
                            + URLEncoder.encode(username,"UTF-8");
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
                            String salonname= rate.getString("salonname");
                            String umsg= rate.getString("umsg");
                            String replay= rate.getString("replay");
                            String time= rate.getString("time");


                            user_chat_cardview cs = new user_chat_cardview(username,salonname,umsg,replay,time);
                            cardslist.add(cs);

                        }

                        adapter = new user_msg_show_adapter(cardslist, user_msg_show.this,username);
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
