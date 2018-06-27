package com.kjsce.train.cia.Listener;

import com.kjsce.train.cia.Entities.CardEntity;

import java.util.ArrayList;

public interface CardListener
{
    void onCardAdded(CardEntity cardEntity);

    void onCardRemoved(CardEntity cardEntity);

    void onDataChanged(ArrayList<CardEntity> cardEntities);

    void onCardChanged(CardEntity cardEntity);
}
