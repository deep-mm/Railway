package com.example.amey.loginfirebase.Entity.Report;
import com.example.amey.loginfirebase.Entity.Analysis.GeneralTrainAnalysis;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;

import java.util.Arrays;

public class GeneralReport {

    private String reportSubmittedBy;
    private String placeOfInspection;
    private String typeOfInspection;
    private String trainNumber;
    private String trainName;
    private String dateTime;
    private String manufacturer;
    private GeneralCard generalCard;
    private GeneralTrainAnalysis generalTrainAnalysis;

    public GeneralReport() {

    }

    public GeneralReport(String reportSubmittedBy, String placeOfInspection, String typeOfInspection, String trainNumber, String trainName, String dateTime, String manufacturer, GeneralCard generalCard, GeneralTrainAnalysis generalTrainAnalysis) {
        this.reportSubmittedBy = reportSubmittedBy;
        this.placeOfInspection = placeOfInspection;
        this.typeOfInspection = typeOfInspection;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.dateTime = dateTime;
        this.manufacturer = manufacturer;
        this.generalCard = generalCard;
        this.generalTrainAnalysis = generalTrainAnalysis;
    }

    public String getReportSubmittedBy() {
        return reportSubmittedBy;
    }

    public void setReportSubmittedBy(String reportSubmittedBy) {
        this.reportSubmittedBy = reportSubmittedBy;
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

    public GeneralCard getGeneralCard() {
        return generalCard;
    }

    public void setGeneralCard(GeneralCard generalCard) {
        this.generalCard = generalCard;
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
                "reportSubmittedBy='" + reportSubmittedBy + '\'' +
                ", placeOfInspection='" + placeOfInspection + '\'' +
                ", typeOfInspection='" + typeOfInspection + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", generalCard=" + generalCard +
                ", generalTrainAnalysis=" + generalTrainAnalysis +
                '}';
    }
}
