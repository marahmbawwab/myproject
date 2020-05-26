package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class gallary extends AppCompatActivity {
    Bitmap [] images;
    GridView gridView;
    String[] myStrings ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
       final ConstraintLayout lay = (ConstraintLayout)findViewById(R.id.layout);
        Intent intent = getIntent();
         myStrings = intent.getStringArrayExtra("images");
       gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(this, myStrings));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
               // v.setDrawingCacheEnabled(true);
                //lay.setVisibility(View.GONE);
            }
        });
    }
    }
class MyAdapter extends BaseAdapter {
    private Context context;
    String [] images ;
    public MyAdapter(Context context, String[] im) {
        this.context = context;
        this.images = im;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
            gridView = inflater.inflate(R.layout.row_image, null);
            ImageView flag = gridView .findViewById(R.id.flag);
        flag.setImageBitmap(BitmapFactory.decodeFile( Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString()+"/beauty_touch/"+images[position]));
        return gridView;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

}
