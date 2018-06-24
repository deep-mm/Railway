package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.IndexEntryEntity;

import java.util.ArrayList;

public interface IdListener
{
    void onIdListChanged(ArrayList<IndexEntryEntity> idList);
}
