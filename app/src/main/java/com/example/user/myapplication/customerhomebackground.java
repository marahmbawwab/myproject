package com.example.user.myapplication;

import android.app.AlertDialog;
        import android.content.*;
        import android.content.Intent;
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

        import static android.support.v4.content.ContextCompat.startActivity;

public class  customerhomebackground  extends AsyncTask<String,Void,String> {
    AlertDialog alertd;
    Context context ;
        customerhomebackground ( Context cx){
        context= cx;
    }
    @Override
    protected String doInBackground(String ... params) {
        String type= params[0];
        String login_url = "http://192.168.1.16/login.php";
        String signup_url = "http://192.168.1.16/signupc.php";
        String forgortcustomer_url="http://192.168.1.16/forgotc.php";
        if(type.equals("login")){
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpurlc = (HttpURLConnection) url.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoOutput(true);
                httpurlc.setDoInput(true);
                OutputStream outputstream = httpurlc.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="
                        +URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

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
        if (type.equals("addcustomer")){
            try {
                String username = params[1];
                String password = params[2];
                String fname = params[3];
                String lname = params[4];
                String email = params[5];
                String phone = params[6];
                String birth = params[7];
                URL urlcreate =  new URL(signup_url);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata = URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+ URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("emailaddress","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+ URLEncoder.encode("userphone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+ URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+ URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode( birth ,"UTF-8")+"&"+URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname ,"UTF-8");
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
        if (type.equals("forgotcustomer")){
            try {
                String username = params[1];
                String password = params[2];
                URL urlcreate =  new URL(forgortcustomer_url);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata = URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+ URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
