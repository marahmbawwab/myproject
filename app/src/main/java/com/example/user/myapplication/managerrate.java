package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;

public class managerrate  extends AppCompatActivity {
    private RecyclerView rview;
    private rates_adapter adapter;
    private List<rates_cardsview> cardslist;
    private SearchView search;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerrate);
        rview = (RecyclerView) findViewById(R.id.recycleview1);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        cardslist = new ArrayList<>();
        getrates();
    }

    private List<rates_cardsview> filter(List<rates_cardsview> list, String Query){

        Query=Query.toLowerCase();
        final List<rates_cardsview> filteredlist = new ArrayList<>();
        for(rates_cardsview model: list){

            final String salon_name = model.getDes().toLowerCase();
            if(salon_name.startsWith(Query)){
                filteredlist.add(model);
            }


        }

        return filteredlist;
    }

    private void changesearchtextcolor(View v){

        if(v!=null){

            if(v instanceof TextView){
                ((TextView) v).setTextColor(Color.BLUE);
                return;
            }
            else if (v instanceof ViewGroup){
                ViewGroup vg = (ViewGroup) v;
                for(int i=0;i<vg.getChildCount();i++){
                    changesearchtextcolor(vg.getChildAt(i));
                }
            }
        }




    }


    public void getrates(){
        connection conn= new connection(this);
        String type="getrates";
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = spreferences.getString("user", null);

        try {
            String result = conn.execute(type,name).get();
            adapter = new rates_adapter(cardslist, managerrate.this);
            rview.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class connection extends AsyncTask<String, String, String> {
        int i;
        String result ="";
        String types="";
        String line="";
        Context c;
        public connection(managerrate mainActivity) {
            c=mainActivity;
        }

        @Override
        protected String doInBackground(String... strings) {
            result = "";
            String type = strings[0];
            String name =strings[1];
            types=type;
            String host = "http://192.168.1.114/getsalonrates.php";
            if (type.equals("getrates")) {
                try {
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                     connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter bufferwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                    String postdata= URLEncoder.encode("salon","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                    bufferwriter.write(postdata);
                    bufferwriter.flush();
                    bufferwriter.close();
                    outputstream.close();
                    InputStream inputstream = connection.getInputStream();
                    BufferedReader bufferreader= new BufferedReader(new InputStreamReader(inputstream));
                    StringBuffer buffer = new StringBuffer("");
                    line = "";
                    while ((line = bufferreader.readLine()) != null) {
                        buffer.append(line+"\n");
                        break;
                    }
                    bufferreader.close();
                    result = buffer.toString();
                } catch (Exception e) {
                    return new String("there is exception:" + e.getMessage());
                }
                return result;
            }
            return "sorry";
        }
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {
            if (types.equals("getrates")) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray js = jsonObject.getJSONArray("rates");

                        for(  i =0;i<js.length();i++)
                        {
                            JSONObject rate = js.getJSONObject(i);
                            String user_name= rate.getString("username");
                            String salon_name= rate.getString("salonname");
                            String rates= rate.getString("ratestar");
                            String msg= rate.getString("feedback");
                            rates_cardsview cs = new rates_cardsview(user_name,salon_name,rates,msg);
                            cardslist.add(cs);

                        }

                        adapter = new rates_adapter(cardslist, managerrate.this);
                        rview.setAdapter(adapter);

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }
}
