package com.kjsce.train.cia.Listeners;

import java.util.List;

//ye bhi final wala
public class GeneralCard {

    private String submittedBy;
    private String type;
    private List<String> image;
    private List<String> audio;
    private String comment;
    private boolean problemStatus;

    public GeneralCard() {
    }

    public GeneralCard(String submittedBy, String type, List<String> image, List<String> audio, String comment, boolean problemStatus) {
        this.submittedBy = submittedBy;
        this.type = type;
        this.image = image;
        this.audio = audio;
        this.comment = comment;
        this.problemStatus = problemStatus;
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

    @Override
    public String toString() {
        return "GeneralCard{" +
                "submittedBy='" + submittedBy + '\'' +
                ", type='" + type + '\'' +
                ", image=" + image +
                ", audio=" + audio +
                ", comment='" + comment + '\'' +
                ", problemStatus=" + problemStatus +
                '}';
    }

    public boolean equals(GeneralCard g1, GeneralCard g2){
        if(g1.submittedBy.equals(g2.submittedBy) && g1.type.equals(g2.type) && g1.comment.equals(g2.comment) && (g1.problemStatus == g2.problemStatus)){
            return true;
        }
        return false;
    }

    public void copy(GeneralCard generalCard){
        this.submittedBy = generalCard.submittedBy;
        this.type = generalCard.type;
        this.comment = generalCard.comment;
        this.problemStatus = generalCard.problemStatus;
    }
}
