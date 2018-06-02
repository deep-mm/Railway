package com.kjsce.train.cia.Entity.Problem;

import java.util.ArrayList;

public class CoachExteriorProblem extends Problem
{
    boolean paintQuality,bogeyCleanliness,roofCleanliness,windowGlass;

    public CoachExteriorProblem(boolean paintQuality, boolean bogeyCleanliness, boolean roofCleanliness, boolean windowGlass) {
        this.paintQuality = paintQuality;
        this.bogeyCleanliness = bogeyCleanliness;
        this.roofCleanliness = roofCleanliness;
        this.windowGlass = windowGlass;
    }

    public CoachExteriorProblem() {
    }

    public ArrayList<String> getTypes(){
        ArrayList<String> types = new ArrayList<String>();

        types.add("Paint Quality");
        types.add("Body Cleanliness");
        types.add("Roof Cleanliness");
        types.add("Window Glass");
        return types;
    }

    public boolean isPaintQuality() {
        return paintQuality;
    }

    public void setPaintQuality(boolean paintQuality) {
        this.paintQuality = paintQuality;
    }

    public boolean isBogeyCleanliness() {
        return bogeyCleanliness;
    }

    public void setBogeyCleanliness(boolean bogeyCleanliness) {
        this.bogeyCleanliness = bogeyCleanliness;
    }

    public boolean isRoofCleanliness() {
        return roofCleanliness;
    }

    public void setRoofCleanliness(boolean roofCleanliness) {
        this.roofCleanliness = roofCleanliness;
    }

    public boolean isWindowGlass() {
        return windowGlass;
    }

    public void setWindowGlass(boolean windowGlass) {
        this.windowGlass = windowGlass;
    }

    @Override
    public String toString() {
        return "CoachExteriorProblem{" +
                "paintQuality=" + paintQuality +
                ", bogeyCleanliness=" + bogeyCleanliness +
                ", roofCleanliness=" + roofCleanliness +
                ", windowGlass=" + windowGlass +
                '}';
    }
}
