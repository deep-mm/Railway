package com.example.amey.loginfirebase.Entity.Card;

import com.example.amey.loginfirebase.Entity.Problem.Problem;

import java.util.List;

//ye bhi bas final
public class DetailedCard {

    private String submittedBy;
    private String type;
    private List<String> image;
    private List<String> audio;
    private String comment;
    private boolean problemStatus;
    private Problem problem;

    public DetailedCard() {
    }

    public DetailedCard(String submittedBy, String type, List<String> image, List<String> audio, String comment, boolean problemStatus, Problem problem) {
        this.submittedBy = submittedBy;
        this.type = type;
        this.image = image;
        this.audio = audio;
        this.comment = comment;
        this.problemStatus = problemStatus;
        this.problem = problem;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
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

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "DetailedCard{" +
                "submittedBy='" + submittedBy + '\'' +
                ", type='" + type + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                ", comment='" + comment + '\'' +
                ", problemStatus=" + problemStatus +
                ", problem=" + problem +
                '}';
    }
}

