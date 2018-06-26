package com.kjsce.train.cia.Activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.kjsce.train.cia.Adapter.NotificationsAdapter;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.Utilities.NotificationUtility;

import java.util.List;

public class BackgroundService extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public static NotificationUtility notificationUtility;
    public SharedData sharedData;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sharedData = new SharedData(getApplicationContext());
        notificationUtility = new NotificationUtility(sharedData.getUserEntity().getMobileNumber(), new OnNewNotificationAddedListener() {
            @Override
            public void onNotificationAdded(UserNotificationEntity newUserNotification) {
                //TODO: Display Notification
            }
        },
                new OnNotificationListChangeListener() {
                    @Override
                    public void OnDataChanged(List<UserNotificationEntity> newNotificationList) {
                        sharedData.setNotificationEntityList(newNotificationList);
                        NotificationsAdapter notificationsAdapter = new NotificationsAdapter(newNotificationList, BackgroundService.this);
                        Notifications.details.setAdapter(notificationsAdapter);
                    }
                });
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
