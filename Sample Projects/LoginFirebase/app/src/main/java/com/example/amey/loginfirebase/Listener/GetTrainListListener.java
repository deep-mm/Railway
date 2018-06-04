package com.example.amey.loginfirebase.Listener;

import com.example.amey.loginfirebase.Entity.TrainEntity;

import java.util.List;

public interface GetTrainListListener {
    void onComplete(List<TrainEntity> trainEntityList);

}
