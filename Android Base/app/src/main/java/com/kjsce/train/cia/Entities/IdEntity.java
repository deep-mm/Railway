package com.kjsce.train.cia.Entities;

public class IdEntity
{
    private String subtype;
    private boolean problemStatus;
    private int numberOfCards;
    private int priority;

    public IdEntity() {
    }

    public IdEntity(String subtype, boolean problemStatus, int numberOfCards, int priority) {
        this.subtype = subtype;
        this.problemStatus = problemStatus;
        this.numberOfCards = numberOfCards;
        this.priority = priority;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public boolean isProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(boolean problemStatus) {
        this.problemStatus = problemStatus;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "IdEntity{" +
                "subtype='" + subtype + '\'' +
                ", problemStatus=" + problemStatus +
                ", numberOfCards=" + numberOfCards +
                ", priority=" + priority +
                '}';
    }
}
