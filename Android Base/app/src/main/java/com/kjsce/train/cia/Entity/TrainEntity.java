package com.kjsce.train.cia.Entity;

public class TrainEntity {
    private String trainNumber;
    private String trainName;
    private String from;
    private String to;
    private String depot;
    private String compartments;
    private String rakes;
    private String manufacturer;
    private String obhs;
    private String kms;
    private String maintainanceType;
    private int daysOfOperation;

    public TrainEntity() {
    }

    public TrainEntity(String trainNumber, String trainName, String from, String to, String depot, String compartments, String rakes, String manufacturer, String obhs, String kms, String maintainanceType, int daysOfOperation) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.from = from;
        this.to = to;
        this.depot = depot;
        this.compartments = compartments;
        this.rakes = rakes;
        this.manufacturer = manufacturer;
        this.obhs = obhs;
        this.kms = kms;
        this.maintainanceType = maintainanceType;
        this.daysOfOperation = daysOfOperation;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getCompartments() {
        return compartments;
    }

    public void setCompartments(String compartments) {
        this.compartments = compartments;
    }

    public String getRakes() {
        return rakes;
    }

    public void setRakes(String rakes) {
        this.rakes = rakes;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getObhs() {
        return obhs;
    }

    public void setObhs(String obhs) {
        this.obhs = obhs;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getMaintainanceType() {
        return maintainanceType;
    }

    public void setMaintainanceType(String maintainanceType) {
        this.maintainanceType = maintainanceType;
    }

    public int getDaysOfOperation() {
        return daysOfOperation;
    }

    public void setDaysOfOperation(int daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "trainName='" + trainName + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", depot='" + depot + '\'' +
                ", compartments='" + compartments + '\'' +
                ", rakes='" + rakes + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", obhs='" + obhs + '\'' +
                ", kms='" + kms + '\'' +
                ", maintainanceType='" + maintainanceType + '\'' +
                ", daysOfOperation=" + daysOfOperation +
                '}';
    }
}
