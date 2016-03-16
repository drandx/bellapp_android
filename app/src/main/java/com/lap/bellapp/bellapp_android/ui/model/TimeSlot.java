package com.lap.bellapp.bellapp_android.ui.model;

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
        //Calendar todayCalendar = Calendar.getInstance();
        //long todayInMillis = todayCalendar.get(Calendar.MILLISECOND);
        long time = System.currentTimeMillis();
        long initialTimeInMillis = initialTime.getTime();
        boolean passDate = time > initialTimeInMillis;

        return isBusy | passDate;
    }

    public void setIsBusy(Boolean isBusy) {
        this.isBusy = isBusy;
    }
}
