package com.example.user.myapplication;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.io.File;
public class third extends AppCompatActivity  implements MediaScannerConnection.MediaScannerConnectionClient {
    Button userp, feedinfo, contactsalon, hair, appoint, contact, about, vision;
    public String[] allFiles;
    private String SCAN_PATH ;
    private static final String FILE_TYPE = "image/*";
    private MediaScannerConnection conn;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show:
                startScan();
                Bundle b=new Bundle();
                b.putStringArray("images", allFiles);
             //   if(allFiles.length==null) there is no images to show
               Intent intent = new Intent(getApplicationContext(),gallary.class);
                intent.putExtras(b);
               startActivity(intent);
                return (true);
         case R.id.location:
            //  Intent intent1 = new Intent(getApplicationContext(),map_activity.class);
            //  startActivity(intent1);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        userp = (Button) findViewById(R.id.button5);
        feedinfo = (Button) findViewById(R.id.button8);
        contactsalon = (Button) findViewById(R.id.button6);
        hair = (Button) findViewById(R.id.button7);
        appoint = (Button) findViewById(R.id.button9);
        contact = (Button) findViewById(R.id.button3);
        about = (Button) findViewById(R.id.button13);
        vision = (Button) findViewById(R.id.button14);
      String r=  Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File folder = new File(r+"/beauty_touch");
        allFiles = folder.list();
        for(int i=0;i<allFiles.length;i++)
        {
          Log.d("all file path"+i, allFiles[i]+allFiles.length);
        }
      SCAN_PATH=Environment.getExternalStorageDirectory().toString()+"/beauty_touch" ; //+allFiles[0];

        userp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent nextScreen = new Intent(getApplicationContext(), account.class);
                Intent nextScreen = new Intent(getApplicationContext(), edituser.class);
                startActivity(nextScreen);

            }
        });
        contactsalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent nextScreen = new Intent(getApplicationContext(), choice.class);
            // Intent nextScreen = new Intent(getApplicationContext(), salon_main.class);
           //     Intent nextScreen = new Intent(getApplicationContext(), saloninfo.class);
                startActivity(nextScreen);
            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), appointpage.class);
                startActivity(nextScreen);

            }
        });
        feedinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent nextScreen = new Intent(getApplicationContext(), rateus.class);
                Intent nextScreen = new Intent(getApplicationContext(), basic_rate.class);
                startActivity(nextScreen);

            }
        });
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), choosehair.class);
                startActivity(nextScreen);

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), about.class);
                startActivity(nextScreen);
            }
        });
        vision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), vision.class);
                startActivity(nextScreen);

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), start_chat.class);
               // Intent nextScreen = new Intent(getApplicationContext(), contact.class);
                startActivity(nextScreen);

            }
        });

    }
    private void startScan()
    {
      //  Log.d("Connected","success"+conn);
        if(conn!=null)
        {
            conn.disconnect();
        }
        conn = new MediaScannerConnection(this,this);
        conn.connect();
    }
    @Override
    public void onMediaScannerConnected() {
        //Log.d("onMediaScannerConnected","success"+conn);
        conn.scanFile(SCAN_PATH, FILE_TYPE);
    }
    @Override
    public void onScanCompleted(String path, Uri uri) {
        try {
          //  Log.d("onScanCompleted",uri + "success"+conn);
            if (uri != null)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        } finally
        {
            conn.disconnect();
            conn = null;
        }
    }
    }


