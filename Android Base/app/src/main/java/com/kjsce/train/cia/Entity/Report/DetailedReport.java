package com.kjsce.train.cia.Entity.Report;

import com.kjsce.train.cia.Entity.Analysis.DetailedTrainAnalysis;
import com.kjsce.train.cia.Entity.BogeyEntity;

public class DetailedReport {
    private String reportSubmittedBy;
    private String placeOfInspection;
    private String typeOfInspection;
    private String trainNumber;
    private String trainName;
    private String dateTime;
    private String manufacturer;
    private BogeyEntity bogeyEntity;
    private DetailedTrainAnalysis detailedTrainAnalysis;


    public DetailedReport(String reportSubmittedBy, String placeOfInspection, String typeOfInspection, String trainNumber, String trainName, String dateTime, String manufacturer, BogeyEntity bogeyEntity, DetailedTrainAnalysis detailedTrainAnalysis) {
        this.reportSubmittedBy = reportSubmittedBy;
        this.placeOfInspection = placeOfInspection;
        this.typeOfInspection = typeOfInspection;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.dateTime = dateTime;
        this.manufacturer = manufacturer;
        this.bogeyEntity = bogeyEntity;
        this.detailedTrainAnalysis = detailedTrainAnalysis;
    }



    public DetailedReport() {
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

    public BogeyEntity getBogeyEntity() {
        return bogeyEntity;
    }

    public void setBogeyEntity(BogeyEntity bogeyEntity) {
        this.bogeyEntity = bogeyEntity;
    }

    public DetailedTrainAnalysis getDetailedTrainAnalysis() {
        return detailedTrainAnalysis;
    }

    public void setDetailedTrainAnalysis(DetailedTrainAnalysis detailedTrainAnalysis) {
        this.detailedTrainAnalysis = detailedTrainAnalysis;
    }

    @Override
    public String toString() {
        return "DetailedReport{" +
                "reportSubmittedBy='" + reportSubmittedBy + '\'' +
                ", placeOfInspection='" + placeOfInspection + '\'' +
                ", typeOfInspection='" + typeOfInspection + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", bogeyEntity=" + bogeyEntity +
                ", detailedTrainAnalysis=" + detailedTrainAnalysis +
                '}';
    }
}
