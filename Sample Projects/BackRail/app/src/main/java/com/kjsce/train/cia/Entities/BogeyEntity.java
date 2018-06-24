package com.kjsce.train.cia.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BogeyEntity
{
    String bogeyNumber;
    ArrayList<ProblemEntity> problemEntities;

    public BogeyEntity(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
        problemEntities = new ArrayList<ProblemEntity>();
    }

    public BogeyEntity(String bogeyNumber, ArrayList<ProblemEntity> problemEntities) {
        this.bogeyNumber = bogeyNumber;
        this.problemEntities = problemEntities;
    }

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
    }

    public ArrayList<ProblemEntity> getProblemEntities() {
        return problemEntities;
    }

    public void setProblemEntities(ArrayList<ProblemEntity> problemEntities) {
        this.problemEntities = problemEntities;
    }

    public void addProblem(ProblemEntity problemEntity){
        problemEntities.add(problemEntity);
    }

    public void removeProblem(ProblemEntity problemEntity){
        problemEntities.remove(problemEntity);
    }

    @Override
    public String toString() {
        return "BogeyEntity{" +
                "bogeyNumber='" + bogeyNumber + '\'' +
                ", problemEntities=" + problemEntities +
                '}';
    }

    /*String bogeyNumber;
    Map<String,ArrayList<IdEntity>> bogeyProblems;

    public BogeyEntity(String bogeyNumber){
        this.bogeyNumber = bogeyNumber;
        bogeyProblems = new HashMap<String,ArrayList<IdEntity>>();
    }

    public void addProblem(CardEntity cardEntity){
        ArrayList<IdEntity> idEntities = bogeyProblems.get(cardEntity.getProblem());
        if(idEntities == null){
            idEntities = new ArrayList<IdEntity>();
            idEntities.add(new IdEntity(cardEntity));
        }
        else{
            boolean flag = false;
            for(int i=0;i<idEntities.size();i++){
                if(idEntities.get(i).getId().equals(cardEntity.getId())){
                    idEntities.get(i).addProblem(cardEntity);
                    flag = true;
                }
            }
            if(!flag)
                idEntities.add(new IdEntity(cardEntity));


        }

        bogeyProblems.put(cardEntity.getProblem(),idEntities);
    }

    public void removeProblem(CardEntity cardEntity){
        ArrayList<IdEntity> idEntities = bogeyProblems.get(cardEntity.getProblem());
        if(idEntities ==  null)
            return;

        for(int i=0;i<idEntities.size();i++){
            if(idEntities.get(i).getId().equals(cardEntity.getId())){
                idEntities.get(i).removeProblem(cardEntity);
                if(idEntities.get(i).getProblemListSize() == 0){
                    idEntities.remove(i);
                }
            }
        }

        bogeyProblems.put(cardEntity.getProblem(),idEntities);
    }

    public void updateProblem(CardEntity cardEntity){
        ArrayList<IdEntity> idEntities = bogeyProblems.get(cardEntity.getProblem());
        if(idEntities == null)
            idEntities.add(new IdEntity(cardEntity));
        else{
            boolean flag = false;
            for(int i=0;i<idEntities.size();i++){
                if(idEntities.get(i).getId().equals(cardEntity.getId())){
                    idEntities.get(i).updateProblem(cardEntity);
                    flag = true;
                }
            }
            if(!flag)
                idEntities.add(new IdEntity(cardEntity));
        }

        bogeyProblems.put(cardEntity.getProblem(),idEntities);
    }

    public ArrayList<IdEntity> getIdEntity(String problem){
        return bogeyProblems.get(problem);
    }

    public ArrayList<String> getProblemList(){
        ArrayList<String> problemList = new ArrayList<String>();
        for(Map.Entry<String,ArrayList<IdEntity>> entry:bogeyProblems.entrySet()){
            problemList.add(entry.getKey());
        }
        return problemList;
    }

    public void print(){
        for(Map.Entry<String,ArrayList<IdEntity>> entry:bogeyProblems.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue().size() + ":\n" + entry.getValue() + "\n");
        }
    }*/
}
