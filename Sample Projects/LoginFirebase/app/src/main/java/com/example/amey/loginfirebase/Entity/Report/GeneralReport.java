package com.example.amey.loginfirebase.Entity.Report;
import com.example.amey.loginfirebase.Entity.Analysis.GeneralTrainAnalysis;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;

import java.util.Arrays;
import java.util.List;

//final wala bc

public class GeneralReport {

    private String placeOfInspection;
    private String typeOfInspection;
    private String trainNumber;
    private String trainName;
    private String dateTime;
    private String manufacturer;
    private List<GeneralReport> generalCardList;
    private GeneralTrainAnalysis generalTrainAnalysis;

    public GeneralReport() {
    }

    public GeneralReport(String placeOfInspection, String typeOfInspection, String trainNumber, String trainName, String dateTime, String manufacturer, List<GeneralReport> generalCardList, GeneralTrainAnalysis generalTrainAnalysis) {
        this.placeOfInspection = placeOfInspection;
        this.typeOfInspection = typeOfInspection;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.dateTime = dateTime;
        this.manufacturer = manufacturer;
        this.generalCardList = generalCardList;
        this.generalTrainAnalysis = generalTrainAnalysis;
    }

    public String getPlaceOfInspection() {
        return placeOfInspection;
    }

    public void setPlaceOfInspection(String placeOfInspection) {
        this.placeOfInspection = placeOfInspection;
    }

    public String getTypeOfInspection() {
        return typeOfInspection;
    }

    public void setTypeOfInspection(String typeOfInspection) {
        this.typeOfInspection = typeOfInspection;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<GeneralReport> getGeneralCardList() {
        return generalCardList;
    }

    public void setGeneralCardList(List<GeneralReport> generalCardList) {
        this.generalCardList = generalCardList;
    }

    public GeneralTrainAnalysis getGeneralTrainAnalysis() {
        return generalTrainAnalysis;
    }

    public void setGeneralTrainAnalysis(GeneralTrainAnalysis generalTrainAnalysis) {
        this.generalTrainAnalysis = generalTrainAnalysis;
    }

    @Override
    public String toString() {
        return "GeneralReport{" +
                "placeOfInspection='" + placeOfInspection + '\'' +
                ", typeOfInspection='" + typeOfInspection + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", generalCardList=" + generalCardList +
                ", generalTrainAnalysis=" + generalTrainAnalysis +
                '}';
    }
}
