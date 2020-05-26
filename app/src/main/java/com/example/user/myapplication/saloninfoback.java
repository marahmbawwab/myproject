package com.example.user.myapplication;

import android.content.Context;
import android.os.AsyncTask;

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

public class saloninfoback extends AsyncTask<String,Void,String> {
    Context cont ;
    @Override
    protected String doInBackground(String... strings) {
        String type =strings[0];
        String salonn =strings[1];
        String url ="http://192.168.1.16/showsaloninfo.php";
        String urlapp ="http://192.168.1.16/showsalonapp.php";
        if (type.equals("showsalon")){
            try {
                URL urls =  new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("salonn","UTF-8")+"="+URLEncoder.encode(salonn,"UTF-8");
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
                final JSONObject obj = new JSONObject(sbes);
                String s = "";
                sbes = obj.getString("sname")+"\n"+obj.getString("address")
                        + "\n"+obj.getString("phone")+"\n"+obj.getString("email")+"\n"+obj.getString("feed")+"\n";
                return sbes;

            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("showsalonapp")){
            try {
                URL urls =  new URL(urlapp);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("salonn","UTF-8")+"="+URLEncoder.encode(salonn,"UTF-8");
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
                final JSONObject obj = new JSONObject(sbes);
                String s = "";
                sbes = obj.getString("result");
                return sbes;

            } catch (Exception e) {
                return null;
            }
        }

        return null ;
    }
    saloninfoback(Context con){
        cont = con ;
    }
}
