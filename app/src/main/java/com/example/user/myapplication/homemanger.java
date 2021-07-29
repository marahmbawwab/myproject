package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class homemanger extends AppCompatActivity implements MediaScannerConnection.MediaScannerConnectionClient {
    TextView signup;
    TextView forgott;
    EditText user, pass;
    String username, passward;
    Button signin;
    public String[] allFiles;
    private String SCAN_PATH ;
    private static final String FILE_TYPE = "image/*";
    private MediaScannerConnection conn;
    /* if (allFiles.length == 0) {
        Toast.makeText(this, "thereis no profile picture", Toast.LENGTH_SHORT).show();
    } else {
        startScan();
        pic.setImageBitmap(BitmapFactory.decodeFile( Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString()+"/beauty_touch/"+allFiles[0]));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemanger);
        signup = (TextView) findViewById(R.id.textView2);
        forgott = (TextView) findViewById(R.id.textView9);
        signin = (Button) findViewById(R.id.button);
        user = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editTextValue);
        String r = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File folder = new File(r + "/beauty_profile");
        allFiles = folder.list();
        SCAN_PATH = Environment.getExternalStorageDirectory().toString() + "/beauty_profile"; //+allFiles[0];
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), signupmanager.class);
                startActivity(nextScreen);

            }
        });
        forgott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), forgotmanager.class);
                startActivity(nextScreen);

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startScan();
                    signinfunc(v);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void signinfunc(View v) throws ExecutionException, InterruptedException {
        username = user.getText().toString();
        passward = pass.getText().toString();
      if ((username.equals("")) || (passward.equals(""))) {
           AlertDialog.Builder alert;
       alert = new AlertDialog.Builder(homemanger.this);
           alert.setMessage("Please fill all the fields");
           alert.setTitle("Error message");
            alert.show();
       }
        else {
          backgroundhome back = new backgroundhome(this);
          String myvalue = back.execute("signin", username, passward).get();
          Toast.makeText(this, myvalue, Toast.LENGTH_SHORT).show();
           if (myvalue.contains("sign in successfully")) {
                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                spreferencesEditor.putString("user", username);
                setfacehaircut set = new setfacehaircut(this);
                String image = set.execute("getimage",username).get();
                if (image.equals("null")){  spreferencesEditor.putString("im","noimage");
                    spreferencesEditor .apply();
                    Intent nextScreen = new Intent(getApplicationContext(), managerprofile.class);
                    startActivity(nextScreen);
                }
                else {
                spreferencesEditor.putString("im",image);
                spreferencesEditor .apply();
              // Intent nextScreen = new Intent(getApplicationContext(), thirdmanager.class);
                Intent nextScreen = new Intent(getApplicationContext(), managerprofile.class);
                startActivity(nextScreen);}
          }
      }
    }

    @Override
    public void onMediaScannerConnected() {
        conn.scanFile(SCAN_PATH, FILE_TYPE);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        try {
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
    private void startScan() {
        if(conn!=null)
        {
            conn.disconnect();
        }
        conn = new MediaScannerConnection(this,this);
        conn.connect();
    }
}