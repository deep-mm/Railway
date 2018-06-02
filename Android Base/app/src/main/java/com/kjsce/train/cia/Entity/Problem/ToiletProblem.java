package com.kjsce.train.cia.Entity.Problem;

import java.util.ArrayList;

public class ToiletProblem extends Problem
{
    boolean fittings,floorDrainage,cleanliness,smell,biotoilet;

    public ToiletProblem(boolean fittings, boolean floorDrainage, boolean cleanliness, boolean smell, boolean biotoilet) {
        this.fittings = fittings;
        this.floorDrainage = floorDrainage;
        this.cleanliness = cleanliness;
        this.smell = smell;
        this.biotoilet = biotoilet;
    }

    public ArrayList<String> getTypes(){
        ArrayList<String> types = new ArrayList<String>();

        types.add("Fittings");
        types.add("FloodDrainage");
        types.add("Cleanliness");
        types.add("Smell");
        types.add("BioToilet");
        return types;
    }

    public ToiletProblem() {
    }

    public boolean isFittings() {
        return fittings;
    }

    public void setFittings(boolean fittings) {
        this.fittings = fittings;
    }

    public boolean isFloorDrainage() {
        return floorDrainage;
    }

    public void setFloorDrainage(boolean floorDrainage) {
        this.floorDrainage = floorDrainage;
    }

    public boolean isCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(boolean cleanliness) {
        this.cleanliness = cleanliness;
    }

    public boolean isSmell() {
        return smell;
    }

    public void setSmell(boolean smell) {
        this.smell = smell;
    }

    public boolean isBiotoilet() {
        return biotoilet;
    }

    public void setBiotoilet(boolean biotoilet) {
        this.biotoilet = biotoilet;
    }

    @Override
    public String toString() {
        return "ToiletProblem{" +
                "fittings=" + fittings +
                ", floorDrainage=" + floorDrainage +
                ", cleanliness=" + cleanliness +
                ", smell=" + smell +
                ", biotoilet=" + biotoilet +
                '}';
    }
}
