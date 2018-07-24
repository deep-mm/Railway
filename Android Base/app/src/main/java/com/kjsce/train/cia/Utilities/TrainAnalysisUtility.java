package com.kjsce.train.cia.Utilities;

import com.kjsce.train.cia.Entities.BogeyAnalysisEntity;
import com.kjsce.train.cia.Listener.GetBogeyAnalysisListener;
import com.kjsce.train.cia.Listener.GetTrainAnalysisListener;

import java.util.ArrayList;

public class TrainAnalysisUtility
{
    public void getTrainAnalysis(ArrayList<String> bogeyNumbers, GetTrainAnalysisListener listener){
        AnalysisUtility analysisUtility = new AnalysisUtility();

        ArrayList<BogeyAnalysisEntity> analysisEntities = new ArrayList<>();
        for(int i=0;i<bogeyNumbers.size();i++){
            analysisUtility.getBogeyAnalysis(bogeyNumbers.get(i), new GetBogeyAnalysisListener() {
                @Override
                public void onCompleteTask(BogeyAnalysisEntity bogeyAnalysisEntity) {
                    analysisUtility.detachListener(bogeyAnalysisEntity.getBogeyNumber());
                    analysisEntities.add(bogeyAnalysisEntity);
                    if(analysisEntities.size() == bogeyNumbers.size()){
                        listener.onCompleteTask(analysisEntities);
                    }
                }
            });
        }
    }
}
