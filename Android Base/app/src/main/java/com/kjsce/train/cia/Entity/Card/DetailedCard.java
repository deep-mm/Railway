package com.kjsce.train.cia.Entity.Card;

import com.kjsce.train.cia.Entity.Problem.Problem;

import java.util.List;

public class DetailedCard {
    Problem problem;
    private String type;
    private List<String> image;
    private String comment;
    private boolean problemStatus;
    private List<String> audio;
    public DetailedCard()
    {

    }

    public DetailedCard(Problem problem, String type, List<String> image, String comment, boolean problemStatus, List<String> audio) {
        this.problem = problem;
        this.type = type;
        this.image = image;
        this.comment = comment;
        this.problemStatus = problemStatus;
        this.audio = audio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(boolean problemStatus) {
        this.problemStatus = problemStatus;
    }




    @Override
    public String toString() {
        return "GeneralCard{" +
                "type='" + type + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                ", comment=" + comment +
                ", problemStatus=" + problemStatus +
                '}';
    }

}

