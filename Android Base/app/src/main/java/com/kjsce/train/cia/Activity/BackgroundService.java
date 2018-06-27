package com.kjsce.train.cia.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.kjsce.train.cia.Adapter.NotificationsAdapter;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.NotificationUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BackgroundService extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static NotificationUtility notificationUtility;
    public SharedData sharedData;
    private List<UserNotificationEntity> detailedCards;
    public static int id = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        sharedData = new SharedData(getApplicationContext());
        notificationUtility = new NotificationUtility(sharedData.getUserEntity().getMobileNumber(), new OnNewNotificationAddedListener() {
            @Override
            public void onNotificationAdded(UserNotificationEntity newUserNotification) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setContentTitle("New Report Generated!");
                mBuilder.setContentText(newUserNotification.getSender()+" has generated a report of train: "+newUserNotification.getTrainNumber()+" on "+
                        newUserNotification.getDateTime())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(newUserNotification.getSender()+" has generated a report of train: "+newUserNotification.getTrainNumber()+" on "+
                                getDate(newUserNotification.getDateTime())))
                .setPriority(Notification.PRIORITY_MAX);

                Intent resultIntent = new Intent(context, Notifications.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(Notifications.class);

// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(id, mBuilder.build());
                id++;
            }
        },
                new OnNotificationListChangeListener() {
                    @Override
                    public void OnDataChanged(List<UserNotificationEntity> newNotificationList) {
                        System.out.println("notificationEntity: "+newNotificationList);
                        sharedData.setNotificationEntityList(newNotificationList);
                    }
                });
    }

    @Override
    public void onDestroy() {
       //
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    public String getDate(String date){
        SimpleDateFormat spf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd/MM/yyyy | hh:mm");
        date = spf.format(newDate);
        return date;
    }
}
