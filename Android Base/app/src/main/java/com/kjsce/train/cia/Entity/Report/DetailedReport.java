package com.kjsce.train.cia.Entity.Report;

import com.kjsce.train.cia.Entity.Analysis.DetailedTrainAnalysis;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Card.DetailedCard;

import java.util.ArrayList;
import java.util.List;

//final
public class DetailedReport {

    private String placeOfInspection;
    private String typeOfInspection;
    private String trainNumber;
    private String trainName;
    private String dateTime;
    private String manufacturer;
    private List<BogeyEntity> bogeyEntityList;
    private DetailedTrainAnalysis detailedTrainAnalysis;

    public void addBogeyEntity(BogeyEntity bogeyEntity)
    {
        if(bogeyEntityList==null)
        {
            bogeyEntityList=new ArrayList<BogeyEntity>();
        }
        bogeyEntityList.add(bogeyEntity);
    }
    public DetailedReport() {
    }

    public DetailedReport(String placeOfInspection, String typeOfInspection, String trainNumber, String trainName, String dateTime, String manufacturer, List<BogeyEntity> bogeyEntityList, DetailedTrainAnalysis detailedTrainAnalysis) {
        this.placeOfInspection = placeOfInspection;
        this.typeOfInspection = typeOfInspection;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.dateTime = dateTime;
        this.manufacturer = manufacturer;
        this.bogeyEntityList = bogeyEntityList;
        this.detailedTrainAnalysis = detailedTrainAnalysis;
    }

    public void addDetailedCard(DetailedCard card, String bogeyNumber){
        for(int i=0;i<bogeyEntityList.size();i++)
        {
            if(bogeyEntityList.get(i).getBogeyNumber().equals(bogeyNumber)){
                if(bogeyEntityList.get(i).getDetailedCard()==null)
                {
                    bogeyEntityList.get(i).setDetailedCard(new ArrayList<DetailedCard>());
                }
                    bogeyEntityList.get(i).getDetailedCard().add(card);
            }
        }
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

    public List<BogeyEntity> getBogeyEntityList() {
        return bogeyEntityList;
    }

    public void setBogeyEntityList(List<BogeyEntity> bogeyEntityList) {
        this.bogeyEntityList = bogeyEntityList;
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
                "placeOfInspection='" + placeOfInspection + '\'' +
                ", typeOfInspection='" + typeOfInspection + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", bogeyEntityList=" + bogeyEntityList +
                ", detailedTrainAnalysis=" + detailedTrainAnalysis +
                '}';
    }
}
