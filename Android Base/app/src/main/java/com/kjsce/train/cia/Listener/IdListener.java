package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.IdEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;

import java.util.ArrayList;

public interface IdListener
{
    void onIdListChanged(ArrayList<IndexEntryEntity> idList);

    void onIdAdded(IndexEntryEntity indexEntryEntity);

    void onIdRemoved(IndexEntryEntity indexEntryEntity);

    void onIdChanged(IndexEntryEntity indexEntryEntity);
}
