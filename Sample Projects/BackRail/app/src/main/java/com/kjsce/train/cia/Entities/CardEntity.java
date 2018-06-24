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

    public CardEntity(String dateTime, String sender, String trainNumber) {
        this.dateTime = dateTime;
        this.sender = sender;
        this.trainNumber = trainNumber;
    }

    public CardEntity(String dateTime, String sender, String trainNumber, String comment) {
        this.dateTime = dateTime;
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.comment = comment;
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
    /*
    public CardEntity() {
    }

    public CardEntity(String sender, String trainNumber, String dateTime,String id,String problem) {
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.placeOfInspection = null;
        this.comment = null;
        this.image = null;
        this.audio = null;
        this.dateTime = dateTime;
        this.problem = problem;
        this.subType = null;
        this.id = id;
    }

    public CardEntity(String sender, String trainNumber, String placeOfInspection, String comment, List<String> image, List<String> audio, String dateTime, String problem, String subType) {
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.placeOfInspection = placeOfInspection;
        this.comment = comment;
        this.image = image;
        this.audio = audio;
        this.dateTime = dateTime;
        this.problem = problem;
        this.subType = subType;
    }

    public CardEntity(String sender, String trainNumber, String placeOfInspection, String comment, List<String> image, List<String> audio, String dateTime, String problem, String subType, String id, boolean problemStatus) {
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.placeOfInspection = placeOfInspection;
        this.comment = comment;
        this.image = image;
        this.audio = audio;
        this.dateTime = dateTime;
        this.problem = problem;
        this.subType = subType;
        this.id = id;
        this.problemStatus = problemStatus;
    }

    public CardEntity(String bogeyNumber, String sender, String trainNumber, String placeOfInspection, String comment, List<String> image, List<String> audio, String dateTime, String problem, String subType, String id, boolean problemStatus) {
        this.bogeyNumber = bogeyNumber;
        this.sender = sender;
        this.trainNumber = trainNumber;
        this.placeOfInspection = placeOfInspection;
        this.comment = comment;
        this.image = image;
        this.audio = audio;
        this.dateTime = dateTime;
        this.problem = problem;
        this.subType = subType;
        this.id = id;
        this.problemStatus = problemStatus;
    }

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(boolean problemStatus) {
        this.problemStatus = problemStatus;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", sender='" + sender + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", placeOfInspection='" + placeOfInspection + '\'' +
                ", comment='" + comment + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                ", dateTime='" + dateTime + '\'' +
                ", problem='" + problem + '\'' +
                ", subType='" + subType + '\'' +
                ", id='" + id + '\'' +
                ", problemStatus=" + problemStatus +
                '}';
    }

    public boolean equals(CardEntity cardEntity){
        if(this.sender.equals(cardEntity.sender) && this.trainNumber.equals(cardEntity.trainNumber) && this.dateTime.equals(cardEntity.dateTime)){
            return true;
        }
        return false;
    }

    public void copy(CardEntity cardEntity){
        this.audio = cardEntity.audio;
        this.placeOfInspection = cardEntity.placeOfInspection;
        this.comment = cardEntity.comment;
        this.image = cardEntity.image;
        this.problem = cardEntity.problem;
        this.subType = cardEntity.subType;
        this.id = cardEntity.id;
        this.problemStatus = cardEntity.problemStatus;
        this.bogeyNumber = cardEntity.bogeyNumber;
    }*/
}
