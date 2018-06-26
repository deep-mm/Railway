package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.NotificationUtility;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private NotificationUtility notificationUtility = new NotificationUtility("8888888888", new OnNewNotificationAddedListener() {
        @Override
        public void onNotificationAdded(UserNotificationEntity newUserNotificationEntity) {
            Toast.makeText(getApplicationContext(), "Notification added: "+newUserNotificationEntity.toString(), Toast.LENGTH_LONG).show();
        }
    }, new OnNotificationListChangeListener() {
        @Override
        public void OnDataChanged(List<UserNotificationEntity> newNotificationList) {
            Toast.makeText(getApplicationContext(), "Notification List: "+newNotificationList.toString(), Toast.LENGTH_LONG).show();
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }
    public void getNotificationList(View view)
    {
        Toast.makeText(getApplicationContext(), "Notification LIST: "+notificationUtility.getNotificationList().toString(), Toast.LENGTH_LONG).show();

    }

    public void clearNotificationList(View view)
    {
        notificationUtility.clearNotification();
    }
    public void sendNotification(View view)
    {
        notificationUtility.sendNotification("8888888888",new UserNotificationEntity("123456","Rajdhani","1305","Parakh"));
    }

}
