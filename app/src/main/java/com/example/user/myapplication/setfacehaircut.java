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

public class setfacehaircut extends AsyncTask<String,Void,String> {
    Context cont ;
    setfacehaircut(Context con){
        cont = con ;
    }
    @Override
    protected String doInBackground(String... strings) {
        String type =strings[0];
        String usern =strings[1];
       /* String urlset="http://192.168.1.16/setfaceshape.php";
        String urlget ="http://192.168.1.16/getface.php";
        String urlgetpoint ="http://192.168.1.16/getpoint.php";
        String urlsetpoint ="http://192.168.1.16/setpoint.php";
        String urlsetimage ="http://192.168.1.16/setimage.php";
        String urlgetimage="http://192.168.1.16/getimage.php";*/
        String urlset="http://192.168.1.114/setfaceshape.php";
        String urlget ="http://192.168.1.114/getface.php";
        String urlgetpoint ="http://192.168.1.114/getpoint.php";
        String urlsetpoint ="http://192.168.1.114/setpoint.php";
        String urlsetimage ="http://192.168.1.114/setimage.php";
        String urlgetimage="http://192.168.1.114/getimage.php";
        if (type.equals("set")){
            try {
                String shape =strings[2];
                URL urls =  new URL(urlset);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8")+"&"+URLEncoder.encode("fshape","UTF-8")+"="+URLEncoder.encode(shape,"UTF-8");
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
                return sbes;
            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("getshape")){
            try {
                URL urls =  new URL(urlget);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8");
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
                String sbres =sb.toString().trim();
                return sbres;
            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("getpoint")){
            try {
                String shape =strings[2];
                URL urls =  new URL(urlgetpoint);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("shape","UTF-8")+"="+URLEncoder.encode(shape,"UTF-8");
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
                String sbres =sb.toString().trim();
                final JSONObject obj = new JSONObject(sbres);
               String sbes = obj.getString("result");
                return sbes;
            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("setpoint")){
            try {
                String hair=strings[2];
                String shape=strings[3];
                URL urls =  new URL(urlsetpoint);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata = URLEncoder.encode("hair","UTF-8")+"="+URLEncoder.encode(hair,"UTF-8")+"&"+URLEncoder.encode("shape","UTF-8")+"="+URLEncoder.encode(shape,"UTF-8");
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
                String sbres =sb.toString().trim();
                return sbres;
            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("setimage")){
        try {
            String image =strings[2];
            URL urls =  new URL(urlsetimage);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream out = connection.getOutputStream();
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postdata = URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(image,"UTF-8")+"&"+URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8");
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
            String sbres =sb.toString().trim();
            return sbres;
        } catch (Exception e) {
            return null;
        }
    }
        else if (type.equals("getimage")){
            try {
                URL urls =  new URL(urlgetimage);
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8");
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
                String sbres =sb.toString().trim();
                final JSONObject obj = new JSONObject(sbres);
                String s = "";
                sbres = obj.getString("imm");
                return sbres;
            } catch (Exception e) {
                return null;
            }
        }
        return null ;
          }
         }
