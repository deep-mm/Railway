package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.UserNotificationEntity;

import java.util.List;

public interface OnNotificationListChangeListener {
    void OnDataChanged(List<UserNotificationEntity> newNotificationList);
}
