package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
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
import java.util.concurrent.ExecutionException;

public class gallary extends AppCompatActivity {
    Bitmap [] images;
    GridView gridView;
    String user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
       /* try {
            getim();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

       gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(this, images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v,
                                    int position, long id) {
            }
        });
    }
 public void getim() throws ExecutionException, InterruptedException {
     SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
     user=pref.getString("usern", null) ;
        Saveimage dd = new Saveimage(this);
        String val = dd.execute("get",user).get();
        String [] imagg = val.split("\t");
         /* images = new Bitmap[imagg.length];
        for (int i =0 ;i<imagg.length;i++) {
            images[i]=decodeBase64(imagg[i]);
        }*/
     Toast.makeText(this,imagg[0],Toast.LENGTH_LONG);

    }
    }
class MyAdapter extends BaseAdapter {
    private Context context;
    private final Bitmap[] images;
    public MyAdapter(Context context, Bitmap[] im) {
        this.context = context;
        this.images = im;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
            gridView = inflater.inflate(R.layout.row_image, null);
            ImageView flag = (ImageView) gridView .findViewById(R.id.flag);
        flag.setImageBitmap(images[position]);
             //flag.setImageResource(images[position]);
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
