package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.*;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class  backgroundworker2  extends AsyncTask<String,Void,String> {
    AlertDialog alertd;
    Context context ;
    /* backgroundworker ( Context cx){

         context= cx;
     }*/
    @Override
    protected String doInBackground(String ... params) {
        String type= params[0];
        String login_url = "http://192.168.1.114/login_u.php";
        String register = "http://192.168.1.114/register_u.php";
        if(type.equals("login")){
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputstream = connection.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));

                String post_data = URLEncoder.encode("username","UTF-8")+"="
                        +URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

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


            }
            catch(Exception e){

                e.printStackTrace();
            }
        }

        else if(type.equals("register")){

            try {

                String username = params[1];
                String password = params[2];
                String firstn = params[3];
                String lastn = params[4];
                String emailaddress = params[5];
                String phoneno = params[6];
                String bdate = params[7];

                URL url = new URL(register);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputstream = connection.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));

                String post_data = URLEncoder.encode("username","UTF-8")+"="
                        +URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("fn","UTF-8")+"="+URLEncoder.encode(firstn,"UTF-8")+"&"

                        +URLEncoder.encode("ln","UTF-8")+"="+URLEncoder.encode(lastn,"UTF-8")+"&"

                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(emailaddress,"UTF-8")+"&"

                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phoneno,"UTF-8")+"&"

                        +URLEncoder.encode("bd","UTF-8")+"="+URLEncoder.encode(bdate,"UTF-8");

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
            }
            catch(Exception e){

                e.printStackTrace();
            }
        }







        return null;
    }

    @Override
    protected void onPreExecute() {
        // alertd = new AlertDialog.Builder(context).create();
        // alertd.setTitle("login status");
    }

    @Override
    protected void onPostExecute(String result) {


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}