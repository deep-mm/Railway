package com.kjsce.train.cia.Entities;

import java.util.ArrayList;

public class IdEntity
{
    String id;
    String subtype;
    boolean problemStatus;
    ArrayList<CardEntity> cardEntities;

    public IdEntity(String id, String subtype) {
        this.id = id;
        this.subtype = subtype;
        this.problemStatus = false;
        this.cardEntities = new ArrayList<CardEntity>();
    }

    public IdEntity(String id, String subtype, ArrayList<CardEntity> problemList) {
        this.id = id;
        this.subtype = subtype;
        this.cardEntities = problemList;
        this.problemStatus = false;
    }

    public IdEntity(String id, String subtype, boolean problemStatus, ArrayList<CardEntity> problemList) {
        this.id = id;
        this.subtype = subtype;
        this.problemStatus = problemStatus;
        this.cardEntities = problemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<CardEntity> getcardEntities() {
        return cardEntities;
    }

    public void setcardEntities(ArrayList<CardEntity> cardEntities) {
        this.cardEntities = cardEntities;
    }

    public void addCard(CardEntity cardEntity){
        cardEntities.add(cardEntity);
    }

    public void removeCard(CardEntity cardEntity){
        cardEntities.remove(cardEntity);
    }

    @Override
    public String toString() {
        return "IdEntity{" +
                "id='" + id + '\'' +
                ", subtype='" + subtype + '\'' +
                ", problemStatus=" + problemStatus +
                ", problemList=" + cardEntities +
                '}';
    }


    /* public IdEntity(){
        problemList = new ArrayList<>();
    }

    public IdEntity(CardEntity cardEntity){
        this.id = cardEntity.getId();
        this.subtype = cardEntity.getSubType();
        problemList = new ArrayList<>();
        problemList.add(cardEntity);
    }

    public void addProblem(CardEntity cardEntity){
        problemList.add(cardEntity);
    }

    public void removeProblem(CardEntity cardEntity){
        problemList.remove(cardEntity);
    }

    public void updateProblem(CardEntity cardEntity){
        for(int i=problemList.size()-1;i>=0;i--){
            if(problemList.get(i).equals(cardEntity)){
                problemList.get(i).copy(cardEntity);
            }
        }
    }

    public int getProblemListSize(){
        return problemList.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nIdEntity{" +
                "id='" + id + '\'' +
                ", subtype='" + subtype + '\'' +
                ", problemList Size= " + problemList.size() +
                ", problemList=" + problemList +
                '}';
    }*/
}
