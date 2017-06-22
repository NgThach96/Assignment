package com.example.ngthach96.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.example.ngthach96.activity.ActivityShowNote;
import com.example.ngthach96.activity.MainActivity;
import com.example.ngthach96.MainScreen.R;
import com.example.ngthach96.model.MyNote;

/**
 * Created by NgThach96 on 6/9/2017.
 */

public class NoteBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Notification created",Toast.LENGTH_SHORT).show();
        Bundle bundle = intent.getExtras();
        if(bundle == null) Toast.makeText(context,"bundle null", Toast.LENGTH_SHORT).show();
        MyNote myNote = (MyNote) bundle.getSerializable("myNote");
        if(myNote == null)  Toast.makeText(context,"myNote null", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(myNote.getTitle())
                .setContentText(myNote.getNote());

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        Intent intent1 = new Intent(context, ActivityShowNote.class);
        stackBuilder.addNextIntent(intent1);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(bundle.getInt("int"), notificationBuilder.build());
    }
}