package com.kjsce.train.cia.Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class BogeyAnalysisEntity
{
    HashMap<String,AnalysisEntity> analysis;
    String bogeyNumber;

    public BogeyAnalysisEntity(String bogeyNumber){
        analysis = new HashMap<>();
        this.bogeyNumber = bogeyNumber;
    }

    public void addProblemAnalysis(String problem,AnalysisEntity analysisEntity){
        analysis.put(problem,analysisEntity);
    }

    public AnalysisEntity getProblemAnalysis(String problem){
        return analysis.get(problem);
    }

    public ArrayList<String> getProblemList(){
        return new ArrayList<>(analysis.keySet());
    }

    public String toString(){
        String string = "";
        ArrayList<String> keys = getProblemList();
        for(int i=0;i<keys.size();i++){
            string = string + "\n" + keys.get(i) + " : " + getProblemAnalysis(keys.get(i)).toString();
        }
        return string;
    };

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
    }
}
