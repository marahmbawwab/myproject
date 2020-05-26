package com.example.user.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class activity_grid_item extends AppCompatActivity {
    String[] points ={"d","d2","d3","d4","d5","dd"};
    String user ,s ;
    int[] roundcutts = {R.drawable.round1,R.drawable.round2,R.drawable.round3,R.drawable.round4,R.drawable.round5,R.drawable.round6,R.drawable.round7};
    int[] heartcutts = {R.drawable.heart1,R.drawable.heart2,R.drawable.heart3,R.drawable.heart4,R.drawable.heart5,R.drawable.heart6};
    int[] squarecutts = {R.drawable.square1,R.drawable.square2,R.drawable.square3,R.drawable.square4,R.drawable.square5,R.drawable.square6,R.drawable.square7,R.drawable.square8,R.drawable.square9,R.drawable.square11,R.drawable.square12};
    int[] oblongcutts = {R.drawable.oblong1,R.drawable.oblong2,R.drawable.oblong3,R.drawable.oblong4,R.drawable.oblong5,R.drawable.oblong6,R.drawable.oblong7,R.drawable.oblong8,R.drawable.oblong9,R.drawable.oblong10};
    int[] ovalcutts = {R.drawable.oval1,R.drawable.oval2,R.drawable.oval3,R.drawable.oval4,R.drawable.oval5,R.drawable.oval6,R.drawable.oval7,R.drawable.oval8,R.drawable.oval9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        try {
          points = getpoints();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //finding listview
        GridView gridView = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
              //  Toast.makeText(getApplicationContext(),points[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),viewcuts.class);
                intent.putExtra("name",points[i]);
                if (s.equals("round")) {
                    intent.putExtra("image",roundcutts[i]);
                    intent.putExtra("hair",String.valueOf(i+1));
                }
                else if (s.equals("oval")) {
                    intent.putExtra("image",ovalcutts[i]);
                    intent.putExtra("hair",String.valueOf(i+1));
                }
                else if (s.equals("heart")) {
                    intent.putExtra("image",heartcutts[i]);
                    intent.putExtra("hair",String.valueOf(i+1));
                }
                else if (s.equals("oblong")) {
                    intent.putExtra("image",oblongcutts[i]);
                    intent.putExtra("hair",String.valueOf(i+1));
                }
                else if (s.equals("square")) {
                    intent.putExtra("image",squarecutts[i]);
                    intent.putExtra("hair",String.valueOf(i+1));
                }
                startActivity(intent);
            } });
    }
    public String [] getpoints() throws ExecutionException, InterruptedException {
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user =spref.getString("usern", null);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       s=pref.getString("shape", null) ;
        setfacehaircut setface = new setfacehaircut(this);
        String myvalue;
        myvalue = setface.execute("getpoint", user,s).get();
      String [] gg = myvalue.split("bb");
      String [] [] index = new String[gg.length][2];
      String [] name = new String[gg.length];
      String pp []= new String[gg.length];
      int j=0 ;
        for (int i=0 ; i < gg.length ; i++){
            index[i]= gg[i].split("\t");
        }
        for (int i=0 ; i < gg.length ; i++){
           name [i]  = index[i][j];
        }
         j =1 ;
        int x ;
      for (int i=0 ; i < gg.length ; i++) {
          x=Integer.parseInt(name[i]);
          pp[x-1] = "points:" + index[x-1][j];
      }
        return pp;
    }
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
         return points.length;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.layout_cut, null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.cuts);
            ImageView image = view1.findViewById(R.id.images);
            name.setText(points[i]);
            if (s.equals("round")) {
                image.setImageResource(roundcutts[i]);
            }
           else if (s.equals("oval")) {
                image.setImageResource(ovalcutts[i]);
            }
           else if (s.equals("heart")) {
                image.setImageResource(heartcutts[i]);
            }
           else if (s.equals("oblong")) {
                image.setImageResource(oblongcutts[i]);
            }
           else if (s.equals("square")) {
                image.setImageResource(squarecutts[i]);
            }
            return view1;
        }
    }

    public static String convertArrayToStringMethod(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
        }
        return stringBuilder.toString();
    }
}
