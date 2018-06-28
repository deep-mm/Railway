package com.kjsce.train.cia.Entities.Problem;

import java.util.Arrays;
import java.util.List;

public class CoachExteriorProblem extends Problem
{
    boolean paintQuality,bogeyCleanliness,roofCleanliness,windowGlass;
    List<String> types;

    public CoachExteriorProblem(boolean paintQuality, boolean bogeyCleanliness, boolean roofCleanliness, boolean windowGlass) {
        this.paintQuality = paintQuality;
        this.bogeyCleanliness = bogeyCleanliness;
        this.roofCleanliness = roofCleanliness;
        this.windowGlass = windowGlass;
    }

    public CoachExteriorProblem() {
    }

    public List<String> getTypes(){
        types = Arrays.asList("Paint Quality","Bogey Cleanliness","Roof Cleanliness","Window Glass");
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
