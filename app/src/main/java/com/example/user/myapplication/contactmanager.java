package com.example.user.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class contactmanager extends AppCompatActivity {
  Button send ;
  EditText salonn ;

  private notificationhelper mnotification ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactmanger);
        send = (Button) findViewById(R.id.button9);
        salonn= (EditText) findViewById(R.id.editText);
        mnotification =new notificationhelper(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendonch1("message recived",salonn.getText().toString());
                Intent nextScreen = new Intent(getApplicationContext(), thirdmanager.class);
                startActivity(nextScreen);
            }
        });
    }
    public void sendonch1(String title ,String msg ){
     //   Intent activityintent =new Intent(this,contact.class);
       // PendingIntent intent = PendingIntent.getActivity(this,1,intentnew,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder nb =mnotification.getchanelnotification(title,msg);
        mnotification.getmanager().notify(1,nb.build());
    }
}
