package com.kjsce.train.cia.Entity;

import com.kjsce.train.cia.Entity.Analysis.DetailedBogeyAnalysis;
import com.kjsce.train.cia.Entity.Card.DetailedCard;

import java.util.List;

public class BogeyEntity {
    private String bogeyNumber;
    private String type;
    private List<DetailedCard> detailedCard;
    private DetailedBogeyAnalysis detailedBogeyAnalysis;

    public BogeyEntity() {

    }

    public BogeyEntity(String bogeyNumber, String type, List<DetailedCard> detailedCard, DetailedBogeyAnalysis detailedBogeyAnalysis) {
        this.bogeyNumber = bogeyNumber;
        this.type = type;
        this.detailedCard = detailedCard;
        this.detailedBogeyAnalysis = detailedBogeyAnalysis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DetailedCard> getDetailedCard() {
        return detailedCard;
    }

    public void setDetailedCard(List<DetailedCard> detailedCard) {
        this.detailedCard = detailedCard;
    }

    public DetailedBogeyAnalysis getDetailedBogeyAnalysis() {
        return detailedBogeyAnalysis;
    }

    public void setDetailedBogeyAnalysis(DetailedBogeyAnalysis detailedBogeyAnalysis) {
        this.detailedBogeyAnalysis = detailedBogeyAnalysis;
    }

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
    }

    @Override
    public String toString() {
        return "BogeyEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", type='" + type + '\'' +
                ", detailedCard=" + detailedCard +
                ", detailedBogeyAnalysis=" + detailedBogeyAnalysis +
                '}';
    }
}
