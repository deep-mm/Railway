package com.kjsce.train.cia.Entities;

public class CardReferenceEntity
{
    private String bogeyNumber;
    private String problem;
    private String id;
    private boolean problemStatus;

    public CardReferenceEntity(String bogeyNumber, String problem, String id, boolean problemStatus) {
        this.bogeyNumber = bogeyNumber;
        this.problem = problem;
        this.id = id;
        this.problemStatus = problemStatus;
    }

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
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

    public boolean isProblemStatus() {
        return problemStatus;
    }

    public String getProblemStatus(){
        if(problemStatus)
            return "Solved";
        else
            return "Unsolved";
    }

    public void setProblemStatus(boolean problemStatus) {
        this.problemStatus = problemStatus;
    }

    @Override
    public String toString() {
        return "CardReferenceEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", problem='" + problem + '\'' +
                ", id='" + id + '\'' +
                ", problemStatus='" + problemStatus + '\'' +
                '}';
    }
}
