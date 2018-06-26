package com.kjsce.train.cia.Entities;

import java.util.List;

public class NotificationEntity {
    private String mobileNumber;
    private List<UserNotificationEntity> userNotificationEntityList;

    public NotificationEntity() {
    }

    public NotificationEntity(String mobileNumber, List<UserNotificationEntity> userNotificationEntityList) {
        this.mobileNumber = mobileNumber;
        this.userNotificationEntityList = userNotificationEntityList;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<UserNotificationEntity> getUserNotificationEntityList() {
        return userNotificationEntityList;
    }

    public void setUserNotificationEntityList(List<UserNotificationEntity> userNotificationEntityList) {
        this.userNotificationEntityList = userNotificationEntityList;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", userNotificationEntityList=" + userNotificationEntityList +
                '}';
    }
}
