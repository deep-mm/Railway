package com.kjsce.train.cia.Entities;

public class IndexEntryEntity
{
    private String id;
    private boolean problemStatus;
    private int numberOfCards;
    private String subtype;
    private int priority;

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

    public IndexEntryEntity(String id, boolean problemStatus, String subtype) {
        this.id = id;
        this.problemStatus = problemStatus;
        this.subtype = subtype;
    }

    public IndexEntryEntity(String id, boolean problemStatus, int numberOfCards, String subtype, int priority) {
        this.id = id;
        this.problemStatus = problemStatus;
        this.numberOfCards = numberOfCards;
        this.subtype = subtype;
        this.priority = priority;
    }

    public IndexEntryEntity(String id, IdEntity idEntity){
        this.id = id;
        this.problemStatus = idEntity.isProblemStatus();
        this.numberOfCards = idEntity.getNumberOfCards();
        this.subtype = idEntity.getSubtype();
        this.priority = idEntity.getPriority();
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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "IndexEntryEntity{" +
                "id='" + id + '\'' +
                ", problemStatus=" + problemStatus +
                ", numberOfCards=" + numberOfCards +
                ", subtype='" + subtype + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof IndexEntryEntity){
            if(this.id.equals(((IndexEntryEntity) o).getId()) &&  this.getSubtype().equals(((IndexEntryEntity) o).getSubtype()))
                return true;
            return false;
        }
        else{
            return false;
        }
    }

}
