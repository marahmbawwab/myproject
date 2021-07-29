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

public class backgroundhome  extends AsyncTask<String, String, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    Context con ;
    AlertDialog alert ;
    String resarray [] ;
    backgroundhome(Context ctx ) {
        con =ctx ;
    }

    @Override
    protected void onProgressUpdate(String ... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        alert = new AlertDialog.Builder(con).create();
        alert.setTitle("create account");
        super.onPostExecute(aVoid);
    }

    @Override
    protected String doInBackground(String... strings) {
        String type=strings[0];
      // String siginurl="http://192.168.1.16/signinmanager.php";
       // String changeurl="http://192.168.1.16/change.php";
        String siginurl="http://192.168.1.114/signinmanager.php";
        String changeurl="http://192.168.1.114/change.php";
       // String siginurl="http://192.168.43.80/signinmanager.php";
        // String changeurl="http://192.168.43.80/change.php";
        String usern=strings[1];
        String pass=strings[2];
        String json;
        if(type.equals("signin")){
            try{
                URL urlcreate=new URL(siginurl);
                HttpURLConnection connection=(HttpURLConnection)urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out=connection.getOutputStream();
                BufferedWriter buffer=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata= URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8")+"&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8") ;
                buffer.write(postdata);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream inputStream=connection.getInputStream();
                StringBuilder sb=new StringBuilder();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while((json=bufferedReader.readLine())!=null){
                    sb.append(json+"\n");
                }
                String sbString=sb.toString().trim();
                return sbString;
            }catch(Exception e){
                return null;
            }
        }
        if(type.equals("change")){
            try{
                URL urlcreate=new URL(changeurl);
                HttpURLConnection connection=(HttpURLConnection)urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out=connection.getOutputStream();
                BufferedWriter buffer=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata= URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8")+"&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                buffer.write(postdata);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream inputStream=connection.getInputStream();
                StringBuilder sb=new StringBuilder();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while((json=bufferedReader.readLine())!=null){
                    sb.append(json+"\n");
                }
                String sbString=sb.toString().trim();
                return sbString;
            }catch(Exception e){
                return null;
            }
        }
        return null;
    }

}
