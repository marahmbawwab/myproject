package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class show_rates extends AppCompatActivity {
    private RecyclerView rview;
    // private RecyclerView.Adapter rates_adapter;
    private rates_adapter adapter;
    private List<rates_cardsview> cardslist;
    private SearchView search;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rates);
        rview = (RecyclerView) findViewById(R.id.recycleview1);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        cardslist = new ArrayList<>();
        getrates();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile,menu);
        final MenuItem item = menu.findItem(R.id.app_bar_search);
        search = (SearchView)item.getActionView();
        changesearchtextcolor(search);
        //((EditText) search.findViewById(R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!search.isIconified()){
                    search.setIconified(true);
                }
                item.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<rates_cardsview> filteredlist= filter(cardslist,newText);
                adapter.set_filter(filteredlist);
                return true;
            }
        });
        return true;
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

        try {
            String result = conn.execute(type).get();
            // Toast.makeText(show_rates.this, result, Toast.LENGTH_LONG).show();
            adapter = new rates_adapter(cardslist, show_rates.this);
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
        public connection(show_rates mainActivity) {
            c=mainActivity;
        }

        @Override
        protected String doInBackground(String... strings) {
            result = "";
            String type = strings[0];
            types=type;
         //   String host = "http://192.168.1.16/getrates.php";
            String host = "http://192.168.1.114/getrates.php";
            if (type.equals("getrates")) {
                //Toast.makeText(show_rates.this, "get", Toast.LENGTH_SHORT).show();
                try {
                    URL url = new URL(host);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    // connection.setDoOutput(true);
                    connection.setDoInput(true);
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

                    //return result;

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

                        adapter = new rates_adapter(cardslist, show_rates.this);
                        rview.setAdapter(adapter);

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {

            // super.onProgressUpdate(values);*/
        }
    }//connclass

}
