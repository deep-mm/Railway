package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.TrainEntity;

import java.util.List;

public interface OnTrainListChangeListener {
    void OnDataChenged(List<String> newTrainList);
}
