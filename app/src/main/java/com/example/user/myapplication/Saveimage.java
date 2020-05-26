package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Saveimage  extends AsyncTask<String,Void,String> {
    Context context ;
    Saveimage ( Context cx){
        context= cx;
    }
    @Override
    protected String doInBackground(String ... params) {
        String type= params[0];
        String save = "http://192.168.1.16/insertpic.php";
        String getall = "http://192.168.1.16/getpic.php";
        if(type.equals("save")){
            try {
                String username = params[1];
                String photo = params[2];
                URL url = new URL(save);
                HttpURLConnection httpurlc = (HttpURLConnection) url.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoOutput(true);
                httpurlc.setDoInput(true);
                OutputStream outputstream = httpurlc.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String post_data = URLEncoder.encode("usern","UTF-8")+"="
                        +URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("im","UTF-8")+"="+URLEncoder.encode(photo,"UTF-8");
                bufferwriter.write( post_data);
                bufferwriter.flush();
                bufferwriter.close();
                outputstream.close();
                InputStream inputstream = httpurlc.getInputStream();
                BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));
                StringBuilder sb = new StringBuilder();
                String json=  "";
                while((json= bufferreader.readLine()) != null){
                    sb.append(json+"\n");
                }
                String sbstr= sb.toString().trim();
                return sbstr;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        if (type.equals("get")){
            try {
                String username = params[1];
                URL urlcreate =  new URL(getall);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata = URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                buffer.write(postdata);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream inputStream = connection.getInputStream();
                StringBuilder sb = new StringBuilder();
                String json ="";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream ));
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                String sbString = sb.toString().trim();
                return sbString ;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
    }
    @Override
    protected void onPostExecute(String result) {
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
