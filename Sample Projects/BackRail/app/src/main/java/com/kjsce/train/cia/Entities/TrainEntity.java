package com.kjsce.train.cia.Entities;

import java.util.List;

public class TrainEntity {
    private List<String> bogeyList;

    public TrainEntity() {
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
