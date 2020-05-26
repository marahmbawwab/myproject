package com.example.user.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class appointbackground extends AsyncTask<String,Void,String> {

    Context cont ;
    appointbackground (Context con){
        cont = con ;
    }
    @Override
    protected String doInBackground(String... strings) {
        String type= strings[0];
        String usern =strings[1];
        String url = "http://192.168.1.16/salonname.php";
        String aurl = "http://192.168.1.16/showapp.php";
        String urlinsert = "http://192.168.1.16/insertappoint.php";
        Date date1=null ;
        Date startdate = null;
        Date enddate =null;

        if(type.equals("getsalonname")){
            try {
                URL salonurl = new URL(url);
                HttpURLConnection httpurlc = (HttpURLConnection) salonurl.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoOutput(true);
                httpurlc.setDoInput(true);
                OutputStream outputstream = httpurlc.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String postdata= URLEncoder.encode("usern","UTF-8")+"="+URLEncoder.encode(usern,"UTF-8");
                bufferwriter.write(postdata);
                bufferwriter.flush();
                bufferwriter.close();
                outputstream.close();
                InputStream inputstream = httpurlc.getInputStream();
                BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));
                StringBuilder sb = new StringBuilder();
                String json=  "";
                while((json= bufferreader.readLine()) != null){
                    sb.append(json);
                }
                String sbstr= sb.toString();
                        //.trim();
           //   JSONArray jArray = new JSONArray(sbstr);
                String s= "";
             //   for(int i=0; i<jArray.length();i++){
                final JSONObject obj = new JSONObject(sbstr);
                    s = s + obj.getString("result") ;

              //  final JSONObject obj = new JSONObject(sbstr);
               // String s = "";
             //   sbstr = obj.getString("name") ;
                return s;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
      else if(type.equals("makeappoint")) {
            String name = strings[1];
            String sname = strings[2];
            String adate = strings[3];
            String atime = strings[4];
            boolean bool = false;
            try {
                bool = getapps(sname, adate, atime);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (bool) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                try {
                    date1 = format.parse(atime);
                    startdate = format.parse("09:00");
                    enddate = format.parse("17:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date1.getTime() >= startdate.getTime() && date1.getTime() <= enddate.getTime()) {
                    try {
                        URL salonurl = new URL(urlinsert);
                        HttpURLConnection httpurlc = (HttpURLConnection) salonurl.openConnection();
                        httpurlc.setRequestMethod("POST");
                        httpurlc.setDoOutput(true);
                        httpurlc.setDoInput(true);
                        OutputStream outputstream = httpurlc.getOutputStream();
                        BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
                        String postdata = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("sname", "UTF-8") + "=" + URLEncoder.encode(sname, "UTF-8") + "&" +
                                URLEncoder.encode("atime", "UTF-8") + "=" + URLEncoder.encode(atime, "UTF-8") + "&" + URLEncoder.encode("adate", "UTF-8") + "=" + URLEncoder.encode(adate, "UTF-8");
                        bufferwriter.write(postdata);
                        bufferwriter.flush();
                        bufferwriter.close();
                        outputstream.close();
                        InputStream inputstream = httpurlc.getInputStream();
                        BufferedReader bufferreader = new BufferedReader(new InputStreamReader(inputstream));
                        StringBuilder sb = new StringBuilder();
                        String json = "";
                        while ((json = bufferreader.readLine()) != null) {
                            sb.append(json);
                        }
                        String sbstr = sb.toString();

                        return sbstr;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    return "we are closed in that time please choose atime between 09:00 and 17:00" ;
                }
            }
            else{
                return "there ia an appointment at that time";
            }
        }
        else if(type.equals("showappoint")){
         //   String name=strings[1];
            String sname =strings[2];
            String adate =strings[3];
        //    String atime =strings[4];
            try {
                URL salonurl = new URL(aurl);
                HttpURLConnection httpurlc = (HttpURLConnection) salonurl.openConnection();
                httpurlc.setRequestMethod("POST");
                httpurlc.setDoOutput(true);
                httpurlc.setDoInput(true);
                OutputStream outputstream = httpurlc.getOutputStream();
                BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String postdata= URLEncoder.encode("adate","UTF-8")+"="+URLEncoder.encode(adate,"UTF-8")+"&"+URLEncoder.encode("sname","UTF-8")+"="+URLEncoder.encode(sname,"UTF-8");
                bufferwriter.write(postdata);
                bufferwriter.flush();
                bufferwriter.close();
                outputstream.close();
                InputStream inputstream = httpurlc.getInputStream();
                BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));
                StringBuilder sb = new StringBuilder();
                String json=  "";
                while((json= bufferreader.readLine()) != null){
                    sb.append(json);
                }
                String sbstr= sb.toString();
                String s= "";
                final JSONObject obj = new JSONObject(sbstr);
                 s = s + obj.getString("result") ;
                return s;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public boolean getapps(String sname ,String date , String time) throws IOException, JSONException, ParseException {
        String urlget = "http://192.168.1.16/getallapp.php";
        URL salonurl = new URL(urlget);
        HttpURLConnection httpurlc = (HttpURLConnection) salonurl.openConnection();
        httpurlc.setRequestMethod("POST");
        httpurlc.setDoOutput(true);
        httpurlc.setDoInput(true);
        OutputStream outputstream = httpurlc.getOutputStream();
        BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
        String postdata = URLEncoder.encode("adate", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("sname", "UTF-8") + "=" + URLEncoder.encode(sname, "UTF-8");
        bufferwriter.write(postdata);
        bufferwriter.flush();
        bufferwriter.close();
        outputstream.close();
        InputStream inputstream = httpurlc.getInputStream();
        BufferedReader bufferreader = new BufferedReader(new InputStreamReader(inputstream));
        StringBuilder sb = new StringBuilder();
        String json = "";
        while ((json = bufferreader.readLine()) != null) {
            sb.append(json);
        }
        String sbstr = sb.toString();
        String s = "";
        final JSONObject obj = new JSONObject(sbstr);
        s = s + obj.getString("result");
        String times[] = s.split("\n");
        if (times[0].equals("there")) {
          return true ;
        } else {
            for (int i = 0; i < times.length; i++) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = format.parse(time);
                Date date2 = format.parse(times[i]);
                long difference = date2.getTime() - date1.getTime();
                int diff = (int) difference;
                if (diff == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
