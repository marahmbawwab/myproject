package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class backgroundworker extends AsyncTask<String, String, String> {

        Context con ;
        AlertDialog alert ;
        String resarray [] ;
        backgroundworker(Context ctx ) {
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
    protected String doInBackground(String ... objects) {
        String type = objects[0];
     //  String createurl ="http://192.168.1.16/insertmanager.php";
       //String editurl ="http://192.168.1.16/editmanager.php";
       // String showurl = "http://192.168.1.16/show.php";
         String createurl ="http://192.168.1.114/insertmanager.php";
         String editurl ="http://192.168.1.114/editmanager.php";
         String showurl = "http://192.168.1.114/show.php";
        String usern = objects[1];
        String address = objects[2];
        String emailaddress = objects[3];
        String salonphone = objects[4];
        String salonpassward = objects[5];
        String saloninfo = objects[6];
        String json;
        if (type.equals("create")){
            try {
                URL urlcreate =  new URL(createurl);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
               String postdata = URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8")+"&"+ URLEncoder.encode("salonpassward","UTF-8")+"="+URLEncoder.encode(salonpassward,"UTF-8")+"&"+
                URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+ URLEncoder.encode("emailaddress","UTF-8")+"="+URLEncoder.encode(emailaddress,"UTF-8")+"&"+ URLEncoder.encode("salonphone","UTF-8")+"="+URLEncoder.encode(salonphone,"UTF-8")+"&"+ URLEncoder.encode("saloninfo","UTF-8")+"="+URLEncoder.encode( saloninfo ,"UTF-8");
               buffer.write(postdata);
               buffer.flush();
               buffer.close();
               out.close();
            // InputStream input = connection.getInputStream() ;
                InputStream inputStream = connection.getInputStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream ));
                while ((json = bufferedReader.readLine()) != null) {
                 sb.append(json + "\n");
               //  resarray[i]= sb.toString();
                }
             //   resarray = loadIntoarray(json);
                // return sb.toString().trim();
                String sbString = sb.toString().trim();
               // String[] ary = sbString.split("sendmsg");
                return sbString ;
            } catch (Exception e) {
                return null;
            }
        }
        else if (type.equals("edit")){
            try {
                URL urlcreate =  new URL(editurl);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream out = connection.getOutputStream();
                BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String postdata =  URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8")+"&"+ URLEncoder.encode("salonpassward","UTF-8")+"="+URLEncoder.encode(salonpassward,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+ URLEncoder.encode("emailaddress","UTF-8")+"="+URLEncoder.encode(emailaddress,"UTF-8")+"&"+ URLEncoder.encode("salonphone","UTF-8")+"="+URLEncoder.encode(salonphone,"UTF-8")+"&"+ URLEncoder.encode("saloninfo","UTF-8")+"="+URLEncoder.encode( saloninfo ,"UTF-8");
                buffer.write(postdata);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream inputStream = connection.getInputStream();
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream ));
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
        else if (type.equals("show")){
            try {
                URL urlcreate =  new URL(showurl);
                HttpURLConnection connection = (HttpURLConnection) urlcreate.openConnection();
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
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json+"\n");
                }
                inputStream.close();
                String sbres =sb.toString().trim();
                    final JSONObject obj = new JSONObject(sbres);
                    String s = "";
                    sbres = obj.getString("username")+"\n"+obj.getString("passward")+"\n"+obj.getString("address")
                         + "\n"+obj.getString("phone")+"\n"+obj.getString("email")+"\n"+obj.getString("feed")+"\n";
                    return sbres;

            } catch (Exception e) {
                return null;
            }
        }

      return null ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    private String[] loadIntoarray(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] responsearray = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            responsearray[i] = obj.getString("sendmsg");
        }
        return responsearray ;
    }
}
