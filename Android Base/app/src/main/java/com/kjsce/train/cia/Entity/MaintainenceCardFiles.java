package com.kjsce.train.cia.Entity;

import java.util.Date;

/**
 * Created by Dhaval on 30-04-2018.
 */

public class MaintainenceCardFiles {

    String train_name;
    String date;

    public MaintainenceCardFiles(String train_name, String date) {
        this.date = date;
        this.train_name = train_name;
    }
    MaintainenceCardFiles()
    {

    }
    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
