package com.kjsce.train.cia.Entities;

public class ProblemReferenceEntity
{
    String bogeyNumber;
    String problem;

    public ProblemReferenceEntity(String bogeyNumber, String problem) {
        this.bogeyNumber = bogeyNumber;
        this.problem = problem;
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

    @Override
    public String toString() {
        return "ProblemReferenceEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", problem='" + problem + '\'' +
                '}';
    }
}
