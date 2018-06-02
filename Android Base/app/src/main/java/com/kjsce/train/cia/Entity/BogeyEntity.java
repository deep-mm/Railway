package com.kjsce.train.cia.Entity;

import com.kjsce.train.cia.Entity.Analysis.DetailedBogeyAnalysis;
import com.kjsce.train.cia.Entity.Card.DetailedCard;

public class BogeyEntity {
    private String type;
    private DetailedCard detailedCard;
    private DetailedBogeyAnalysis detailedBogeyAnalysis;

    public BogeyEntity() {

    }

    public BogeyEntity(String type, DetailedCard detailedCard, DetailedBogeyAnalysis detailedBogeyAnalysis) {
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

    public DetailedCard getDetailedCard() {
        return detailedCard;
    }

    public void setDetailedCard(DetailedCard detailedCard) {
        this.detailedCard = detailedCard;
    }

    public DetailedBogeyAnalysis getDetailedBogeyAnalysis() {
        return detailedBogeyAnalysis;
    }

    public void setDetailedBogeyAnalysis(DetailedBogeyAnalysis detailedBogeyAnalysis) {
        this.detailedBogeyAnalysis = detailedBogeyAnalysis;
    }

    @Override
    public String toString() {
        return "BogeyEntity{" +
                "type='" + type + '\'' +
                ", detailedCard=" + detailedCard +
                ", detailedBogeyAnalysis=" + detailedBogeyAnalysis +
                '}';
    }
}
