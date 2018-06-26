package com.kjsce.train.cia.Entities;

import java.util.ArrayList;
import java.util.List;

public class TrainEntity {
    private String trainNo;
    private List<String> bogeyList;

    public TrainEntity(String trainNo) {
        this.trainNo = trainNo;
        bogeyList = new ArrayList<>();
    }

    public TrainEntity(List<String> bogeyList) {
        this.bogeyList = bogeyList;
    }

    public List<String> getBogeyList() {
        return bogeyList;
    }

    public void setBogeyList(List<String> bogeyList) {
        this.bogeyList = bogeyList;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "bogeyList=" + bogeyList +
                '}';
    }
}
