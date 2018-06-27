package com.kjsce.train.cia.Entities;

import java.util.List;

public class CardEntity
{
    private String dateTime;
    private String sender;
    private String trainNumber;
    private String placeOfInspection;
    private List<String> image;
    private List<String> audio;
    private String comment;

    public CardEntity() {
    }

    public CardEntity(String dateTime, String sender, String trainNumber, String placeOfInspection, List<String> image, List<String> audio, String comment) {
        this.dateTime = dateTime;
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.placeOfInspection = placeOfInspection;
        this.image = image;
        this.audio = audio;
        this.comment = comment;
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

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getPlaceOfInspection() {
        return placeOfInspection;
    }

    public void setPlaceOfInspection(String placeOfInspection) {
        this.placeOfInspection = placeOfInspection;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "dateTime='" + dateTime + '\'' +
                ", sender='" + sender + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", placeOfInspection='" + placeOfInspection + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                ", comment='" + comment + '\'' +
                '}';
    }

    public boolean equals(CardEntity cardEntity){
        if(this.dateTime.equals(cardEntity.getDateTime()) &&
                this.sender.equals(cardEntity.getSender()) &&
                this.trainNumber.equals(cardEntity.getTrainNumber())){
            return true;
        }
        return false;
    }

    public void copy(CardEntity cardEntity){
        this.placeOfInspection = cardEntity.getPlaceOfInspection();
        this.image = cardEntity.getImage();
        this.audio = cardEntity.getAudio();
        this.comment = cardEntity.getComment();
    }
}
