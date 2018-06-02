package com.kjsce.train.cia.Entity.Problem;

public class CoachInteriorCleanProblem extends Problem
{
    boolean gangwayCleanliness,stickers,seatingCleanliness,pests,rodent,bedrollQuality;

    public CoachInteriorCleanProblem(boolean gangwayCleanliness, boolean stickers, boolean seatingCleanliness, boolean pests, boolean rodent, boolean bedrollQuality) {
        this.gangwayCleanliness = gangwayCleanliness;
        this.stickers = stickers;
        this.seatingCleanliness = seatingCleanliness;
        this.pests = pests;
        this.rodent = rodent;
        this.bedrollQuality = bedrollQuality;
    }

    public CoachInteriorCleanProblem() {
    }

    public boolean isGangwayCleanliness() {
        return gangwayCleanliness;
    }

    public void setGangwayCleanliness(boolean gangwayCleanliness) {
        this.gangwayCleanliness = gangwayCleanliness;
    }

    public boolean isStickers() {
        return stickers;
    }

    public void setStickers(boolean stickers) {
        this.stickers = stickers;
    }

    public boolean isSeatingCleanliness() {
        return seatingCleanliness;
    }

    public void setSeatingCleanliness(boolean seatingCleanliness) {
        this.seatingCleanliness = seatingCleanliness;
    }

    public boolean isPests() {
        return pests;
    }

    public void setPests(boolean pests) {
        this.pests = pests;
    }

    public boolean isRodent() {
        return rodent;
    }

    public void setRodent(boolean rodent) {
        this.rodent = rodent;
    }

    public boolean isBedrollQuality() {
        return bedrollQuality;
    }

    public void setBedrollQuality(boolean bedrollQuality) {
        this.bedrollQuality = bedrollQuality;
    }

    @Override
    public String toString() {
        return "CoachInteriorCleanProblem{" +
                "gangwayCleanliness=" + gangwayCleanliness +
                ", stickers=" + stickers +
                ", seatingCleanliness=" + seatingCleanliness +
                ", pests=" + pests +
                ", rodent=" + rodent +
                ", bedrollQuality=" + bedrollQuality +
                '}';
    }
}
