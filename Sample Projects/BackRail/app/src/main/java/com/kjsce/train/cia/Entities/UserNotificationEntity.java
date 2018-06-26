package com.kjsce.train.cia.Entities;

public class UserNotificationEntity {

    private String trainNumber;
    private String trainName;
    private String dateTime;
    private String sender;

    public UserNotificationEntity() {
    }

    public UserNotificationEntity(String trainNumber, String trainName, String dateTime, String sender) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.dateTime = dateTime;
        this.sender = sender;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
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

    @Override
    public String toString() {
        return "UserNotificationEntity{" +
                "trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
