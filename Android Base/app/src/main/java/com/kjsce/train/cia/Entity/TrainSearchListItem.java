package com.kjsce.train.cia.Entity;

public class TrainSearchListItem {

    String tNo;
    String tName;

    public TrainSearchListItem(String tNo, String tName) {
        this.tNo = tNo;
        this.tName = tName;
    }

    public String gettNo() {
        return tNo;
    }

    public String gettName() {
        return tName;
    }
}
