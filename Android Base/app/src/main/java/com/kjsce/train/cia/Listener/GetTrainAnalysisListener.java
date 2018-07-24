package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.BogeyAnalysisEntity;

import java.util.ArrayList;

public interface GetTrainAnalysisListener {
    void onCompleteTask(ArrayList<BogeyAnalysisEntity> bogeyAnalysisEntities);
}
