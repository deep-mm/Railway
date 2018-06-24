package com.kjsce.train.cia.Entities;

import java.util.ArrayList;

public class ProblemEntity
{
    private String problem;
    private ArrayList<IdEntity> idEntities;

    public ProblemEntity(String problem) {
        this.problem = problem;
        this.idEntities = new ArrayList<IdEntity>();
    }

    public ProblemEntity(String problem, ArrayList<IdEntity> idEntities) {
        this.problem = problem;
        this.idEntities = idEntities;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public ArrayList<IdEntity> getIdEntities() {
        return idEntities;
    }

    public void setIdEntities(ArrayList<IdEntity> idEntities) {
        this.idEntities = idEntities;
    }

    public void addId(IdEntity idEntity){
        idEntities.add(idEntity);
    }

    public void removeId(IdEntity idEntity){
        idEntities.remove(idEntity);
    }

    @Override
    public String toString() {
        return "ProblemEntity{" +
                "problem='" + problem + '\'' +
                ", idEntities=" + idEntities +
                '}';
    }
}
