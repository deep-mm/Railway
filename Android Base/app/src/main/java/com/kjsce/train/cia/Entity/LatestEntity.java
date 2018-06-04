package com.kjsce.train.cia.Entity;

public class LatestEntity {
    private String latestTimestamp;

    public LatestEntity() {
    }

    public String getLatestTimestamp() {
        return latestTimestamp;
    }

    public void setLatestTimestamp(String latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    @Override
    public String toString() {
        return "LatestEntity{" +
                "latestTimestamp='" + latestTimestamp + '\'' +
                '}';
    }
}

