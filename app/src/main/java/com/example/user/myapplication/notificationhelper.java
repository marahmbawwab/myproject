package com.example.user.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class notificationhelper extends ContextWrapper {
    public static final String ch1id ="channel";
    public static final String chname="channel1";
    private NotificationManager mManger ;
    public notificationhelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createchannel();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createchannel(){
        NotificationChannel ch = new NotificationChannel(ch1id,chname, NotificationManager.IMPORTANCE_DEFAULT);
        ch.enableLights(true);
        ch.enableVibration(true);
        ch.setLightColor(R.color.colorPrimary);
        ch.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getmanager().createNotificationChannel(ch);
    }
    public NotificationManager getmanager (){
        if (mManger==null){
          mManger =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
       return mManger ;
    }
    public NotificationCompat.Builder getchanelnotification(String title ,String msg){
        Intent intentnew =new Intent(this,contact.class);
        PendingIntent intent = PendingIntent.getActivity(this,1,intentnew,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(),ch1id).setContentTitle(title).setContentText(msg).setSmallIcon(R.drawable.ic_meg).setAutoCancel(true).setContentIntent(intent);
    }
}
