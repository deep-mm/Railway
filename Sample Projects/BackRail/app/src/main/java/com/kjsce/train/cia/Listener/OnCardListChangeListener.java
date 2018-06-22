package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.CardEntity;

import java.util.List;

public interface OnCardListChangeListener {
    void onDataChanged(List<CardEntity> newCardList);
}
