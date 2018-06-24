package com.kjsce.train.cia.Entities;

public class IndexEntryEntity
{
    private String id;
    private boolean problemStatus;
    private int numberOfCards;

    public IndexEntryEntity() {
    }

    public IndexEntryEntity(String id, boolean problemStatus) {
        this.id = id;
        this.problemStatus = problemStatus;
    }

    public IndexEntryEntity(String id, boolean problemStatus, int numberOfCards) {
        this.id = id;
        this.problemStatus = problemStatus;
        this.numberOfCards = numberOfCards;
    }

    public boolean isProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(boolean problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    @Override
    public String toString() {
        return "IndexEntryEntity{" +
                "id='" + id + '\'' +
                ", problemStatus=" + problemStatus +
                ", numberOfCards='" + numberOfCards + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof IndexEntryEntity){
            if(this.id.equals(((IndexEntryEntity) o).getId()) && this.problemStatus == ((IndexEntryEntity) o).isProblemStatus())
                return true;
            return false;
        }
        else{
            return false;
        }
    }
}
