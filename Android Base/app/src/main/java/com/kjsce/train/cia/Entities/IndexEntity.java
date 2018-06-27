package com.kjsce.train.cia.Entities;

import java.util.ArrayList;

public class IndexEntity
{
    private ArrayList<IndexEntryEntity> index;

    public IndexEntity() {
    }

    public IndexEntity(ArrayList<IndexEntryEntity> index) {
        this.index = index;
    }

    public ArrayList<IndexEntryEntity> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<IndexEntryEntity> index) {
        this.index = index;
    }

    public boolean addIndex(IndexEntryEntity indexEntryEntity){
        if(!index.contains(indexEntryEntity)) {
            indexEntryEntity.setNumberOfCards(1);
            index.add(indexEntryEntity);
            return true;
        }
        else{
            int i = index.indexOf(indexEntryEntity);
            index.get(i).setNumberOfCards(index.get(i).getNumberOfCards() + 1);
            System.out.println("Added card " + index.get(i).getNumberOfCards());
            return true;
        }

    }

    public boolean removeIndex(IndexEntryEntity indexEntryEntity){
        if(index.contains(indexEntryEntity)) {
            int i = index.indexOf(indexEntryEntity);
            int temp = index.get(i).getNumberOfCards() - 1;
            if(temp > 0)
                index.get(i).setNumberOfCards(temp);
            else
                index.remove(indexEntryEntity);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "IndexEntity{" +
                "index=" + index +
                '}';
    }
}
