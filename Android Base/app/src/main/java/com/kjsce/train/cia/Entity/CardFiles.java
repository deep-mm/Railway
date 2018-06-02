package com.kjsce.train.cia.Entity;

/**
 * Created by Dhaval on 09-04-2018.
 */

public class CardFiles {
    String seat_no,comment,type;

    public CardFiles(String seat_no, String comment, String type) {
        this.seat_no = seat_no;
        this.comment = comment;
        this.type = type;
    }
    CardFiles()
    {

    }

    public String getSeat_no() {
        return seat_no;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }
}