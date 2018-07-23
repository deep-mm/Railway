package com.kjsce.train.cia.Entities;

public class UserNotificationEntity {
    private String trainNumber;
    private String dateTime;
    private String sender;
    private boolean status;

    public UserNotificationEntity() {
    }

    public UserNotificationEntity(String trainNumber, String dateTime, String sender) {
        this.trainNumber = trainNumber;
        this.dateTime = dateTime;
        this.sender = sender;
    }

    public UserNotificationEntity(String trainNumber, String dateTime, String sender, boolean status) {
        this.trainNumber = trainNumber;
        this.dateTime = dateTime;
        this.sender = sender;
        this.status = status;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserNotificationEntity{" +
                "trainNumber='" + trainNumber + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", sender='" + sender + '\'' +
                ", status=" + status +
                '}';
    }
}
