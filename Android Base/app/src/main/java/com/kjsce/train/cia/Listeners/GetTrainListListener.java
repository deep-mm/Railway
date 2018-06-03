package com.kjsce.train.cia.Listeners;

import com.kjsce.train.cia.Entity.TrainEntity;

import java.util.List;

public interface GetTrainListListener {

    void onCompleteTask(List<TrainEntity> trainEntityList);
}
