package com.kjsce.train.cia.Entities;

public class AnalysisEntity
{
    private int solvedProblems;
    private int unsolvedProblems;
    private int criticalProblems;
    private int mediumProblems;
    private int lowProblems;

    public AnalysisEntity() {
        this.solvedProblems = 0;
        this.unsolvedProblems = 0;
        this.criticalProblems = 0;
        this.mediumProblems = 0;
        this.lowProblems = 0;
    }

    public AnalysisEntity(int solvedProblems, int unsolvedProblems, int criticalProblems, int mediumProblems, int lowProblems) {
        this.solvedProblems = solvedProblems;
        this.unsolvedProblems = unsolvedProblems;
        this.criticalProblems = criticalProblems;
        this.mediumProblems = mediumProblems;
        this.lowProblems = lowProblems;
    }

    public int getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(int solvedProblems) {
        this.solvedProblems = solvedProblems;
    }

    public int getUnsolvedProblems() {
        return unsolvedProblems;
    }

    public void setUnsolvedProblems(int unsolvedProblems) {
        this.unsolvedProblems = unsolvedProblems;
    }

    public int getCriticalProblems() {
        return criticalProblems;
    }

    public void setCriticalProblems(int criticalProblems) {
        this.criticalProblems = criticalProblems;
    }

    public int getMediumProblems() {
        return mediumProblems;
    }

    public void setMediumProblems(int mediumProblems) {
        this.mediumProblems = mediumProblems;
    }

    public int getLowProblems() {
        return lowProblems;
    }

    public void setLowProblems(int lowProblems) {
        this.lowProblems = lowProblems;
    }

    @Override
    public String toString() {
        return "AnalysisEntity{" +
                "solvedProblems=" + solvedProblems +
                ", unsolvedProblems=" + unsolvedProblems +
                ", criticalProblems=" + criticalProblems +
                ", mediumProblems=" + mediumProblems +
                ", lowProblems=" + lowProblems +
                '}';
    }
}
