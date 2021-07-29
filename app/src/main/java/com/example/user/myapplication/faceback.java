package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
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

public class faceback extends AsyncTask<String, String, String> {
    Context con ;
    faceback(Context ctx ) {
        con =ctx ;
    }
    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
     //   String getfaceurl = "http://192.168.1.16/faceget.php";
        String getfaceurl = "http://192.168.1.114/faceget.php";
        if (type.equals("getface")) {
            try {
                URL urls =  new URL(getfaceurl);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("salonn","UTF-8")+"="+URLEncoder.encode("dddd","UTF-8");
                buffer.write(postdata);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream inputStream = connection.getInputStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream ));
                String json ="";
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json+"\n");
                }
                inputStream.close();
                String sbes =sb.toString().trim();
               /* final JSONObject obj = new JSONObject(sbes);
                String s = "";
                sbes = obj.getString("username");*/
                return sbes;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}