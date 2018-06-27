package com.kjsce.train.cia.Activity;

import android.app.NotificationManager;
import android.app.Service;
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

import java.util.List;

public class BackgroundService extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static NotificationUtility notificationUtility;
    public SharedData sharedData;
    private List<UserNotificationEntity> detailedCards;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        sharedData = new SharedData(getApplicationContext());
        notificationUtility = new NotificationUtility(sharedData.getUserEntity().getMobileNumber(), new OnNewNotificationAddedListener() {
            @Override
            public void onNotificationAdded(UserNotificationEntity newUserNotification) {
                System.out.println("notificationEntity: "+newUserNotification);
                //TODO: Display Notification
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(R.drawable.notifications_icon);
                mBuilder.setContentTitle("New Report Generated!");
                mBuilder.setContentText(newUserNotification.getSender()+" has generated a report of train: "+newUserNotification.getTrainNumber()+" on "+
                        newUserNotification.getDateTime());

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// notificationID allows you to update the notification later on.
                mNotificationManager.notify(1, mBuilder.build());
            }
        },
                new OnNotificationListChangeListener() {
                    @Override
                    public void OnDataChanged(List<UserNotificationEntity> newNotificationList) {
                        System.out.println("notificationEntity: "+newNotificationList);
                        sharedData.setNotificationEntityList(newNotificationList);
                    }
                });

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast.makeText(context, "Service is still running", Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast.makeText(context, "Service is still running", Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 10000);
            }
        };
        return Service.START_STICKY;
    }
}
