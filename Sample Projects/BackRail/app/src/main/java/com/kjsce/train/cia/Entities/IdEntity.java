package com.kjsce.train.cia.Entities;

import java.util.ArrayList;

public class IdEntity
{
    String id;
    String subtype;
    ArrayList<CardEntity> problemList;

    public IdEntity(){
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
    }
}
