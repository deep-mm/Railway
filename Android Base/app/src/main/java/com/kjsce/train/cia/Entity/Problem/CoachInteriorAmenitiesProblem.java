package com.kjsce.train.cia.Entity.Problem;

public class CoachInteriorAmenitiesProblem extends Problem
{
    boolean fittings,windowGlass,windowShutter,berth,wallLaminates;

    public CoachInteriorAmenitiesProblem(boolean fittings, boolean windowGlass, boolean windowShutter, boolean berth, boolean wallLaminates) {
        this.fittings = fittings;
        this.windowGlass = windowGlass;
        this.windowShutter = windowShutter;
        this.berth = berth;
        this.wallLaminates = wallLaminates;
    }

    public CoachInteriorAmenitiesProblem() {
    }

    public boolean isFittings() {
        return fittings;
    }

    public void setFittings(boolean fittings) {
        this.fittings = fittings;
    }

    public boolean isWindowGlass() {
        return windowGlass;
    }

    public void setWindowGlass(boolean windowGlass) {
        this.windowGlass = windowGlass;
    }

    public boolean isWindowShutter() {
        return windowShutter;
    }

    public void setWindowShutter(boolean windowShutter) {
        this.windowShutter = windowShutter;
    }

    public boolean isBerth() {
        return berth;
    }

    public void setBerth(boolean berth) {
        this.berth = berth;
    }

    public boolean isWallLaminates() {
        return wallLaminates;
    }

    public void setWallLaminates(boolean wallLaminates) {
        this.wallLaminates = wallLaminates;
    }

    @Override
    public String toString() {
        return "CoachInteriorAmenitiesProblem{" +
                "fittings=" + fittings +
                ", windowGlass=" + windowGlass +
                ", windowShutter=" + windowShutter +
                ", berth=" + berth +
                ", wallLaminates=" + wallLaminates +
                '}';
    }
}
