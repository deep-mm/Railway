package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.CardEntity;

import java.util.List;

/**
 * position: problem/id/(dateTime + sender + trainNumber) cardEntity is changed, added or removed---------- null also possible for general update
 * action: 0-general, 1-added a new child, 2-updated a child, 3-removed a child
*/
public interface OnCardListChangeListener {
    void onDataChanged(BogeyEntity newBogeyEntity,String position,int action);
}
