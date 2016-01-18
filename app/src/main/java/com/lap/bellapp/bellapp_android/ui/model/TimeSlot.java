package com.lap.bellapp.bellapp_android.ui.model;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by juanpablogarcia on 1/16/16.
 */
public class TimeSlot {
    Date initialTime;
    Date finalTime;
    Boolean isBusy;

    public TimeSlot(Date initialTime, Date finalTime, Boolean isBusy) {
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.isBusy = isBusy;
    }

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public Boolean getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Boolean isBusy) {
        this.isBusy = isBusy;
    }
}
