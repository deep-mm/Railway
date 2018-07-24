package com.kjsce.train.cia.Entities;

public class CardEntityToUpload {

    private int identifier;
    private CardEntity cardEntity;
    private String BogeyNumber, problem, id, subTypeSelected;
    private Boolean problemStatus;


    public CardEntityToUpload(int identifier, CardEntity cardEntity, String bogeyNumber, String problem, String id, String subTypeSelected, Boolean problemStatus) {
        this.identifier = identifier;
        this.cardEntity = cardEntity;
        BogeyNumber = bogeyNumber;
        this.problem = problem;
        this.id = id;
        this.subTypeSelected = subTypeSelected;
        this.problemStatus = problemStatus;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public String getBogeyNumber() {
        return BogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        BogeyNumber = bogeyNumber;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubTypeSelected() {
        return subTypeSelected;
    }

    public void setSubTypeSelected(String subTypeSelected) {
        this.subTypeSelected = subTypeSelected;
    }

    public Boolean getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(Boolean problemStatus) {
        this.problemStatus = problemStatus;
    }
}
