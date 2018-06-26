package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.UserNotificationEntity;

public interface OnNewNotificationAddedListener {
    void onNotificationAdded(UserNotificationEntity newUserNotification);
}
