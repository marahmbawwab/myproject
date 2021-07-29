package com.example.user.myapplication;

import android.os.AsyncTask;
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
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.*;

public class send_replay_user {
    private String username;
    private String salonname;
    private  String umsg;
    private String replay;
    private String time;

    public send_replay_user(String username, String salonname, String umsg, String replay, String time) {
        this.username = username;
        this.salonname = salonname;
        this.umsg = umsg;
        this.replay = replay;
        this.time = time;

        String type = "sendreplay";
        connection sendconn= new connection();

        try {
            String result = sendconn.execute(type,salonname,username,umsg,replay,time).get();
            //  makeText(main_chat.this, result, LENGTH_SHORT).show();
            // msgtext.setText("");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



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
        //    String usermsg_host = "http://192.168.1.16/salon_replay.php";
            String usermsg_host = "http://192.168.1.114/salon_replay.php";
            if (type.equals("sendreplay")) {
                String salonname,username,replay,umsg,time;
                salonname = strings[1];
                username=strings[2];
                umsg=strings[3];
                replay= strings[4];
                time= strings[5];
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
                            + URLEncoder.encode("umsg","UTF-8")+"="+ URLEncoder.encode(umsg,"UTF-8")+"&"
                            + URLEncoder.encode("replay","UTF-8")+"="+ URLEncoder.encode(replay,"UTF-8")+"&"
                            + URLEncoder.encode("time","UTF-8")+"="+ URLEncoder.encode(time,"UTF-8");


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

        }

        @Override
        protected void onProgressUpdate(String... values) {

            // super.onProgressUpdate(values);
        }
    }//connclass

}
