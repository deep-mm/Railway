package com.kjsce.train.cia.Entity;

import java.util.Date;

/**
 * Created by Dhaval on 30-04-2018.
 */

public class MaintainenceCardFiles {

    String train_name;
    Date date;

    public MaintainenceCardFiles(String train_name, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
