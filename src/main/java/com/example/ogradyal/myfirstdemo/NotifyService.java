package com.example.ogradyal.myfirstdemo;

import android.app.Notification;
import android.app.Service;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by ogradyal on 19/02/17.
 */

public class NotifyService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        //Notification mNotify = new Notification.Builder(this)
          //      .setContentTitle("Log Steps!")
           //     .setContentText("Log your steps for today")
           //     .setSmallIcon(R.mipmap.ic_launcher)
          //      .setContentIntent(pIntent)
           //     .setSound(sound)
                //.addAction(0, "Load Website", pIntent)
           //     .build();

        Notification mNotify = new NotificationCompat.Builder(this)
                .setContentIntent(pIntent)
                .setContentTitle("Log Steps!")
                .setContentText("Log your steps for today")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setSound(sound)
                //.addAction(0, "Load Website", pIntent)
                .build();
        Toast.makeText(getBaseContext(), "in notify service", Toast.LENGTH_LONG).show();
        mNM.notify(1, mNotify);
    }
}
