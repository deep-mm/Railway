package com.kjsce.train.cia.Entities;

public class IdReferenceEntity
{
    private String bogeyNumber;
    private String problem;
    private String id;

    public IdReferenceEntity(String bogeyNumber, String problem, String id) {
        this.bogeyNumber = bogeyNumber;
        this.problem = problem;
        this.id = id;
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

    @Override
    public String toString() {
        return "IdReferenceEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", problem='" + problem + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
