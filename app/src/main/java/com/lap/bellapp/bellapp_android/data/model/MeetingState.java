package com.lap.bellapp.bellapp_android.data.model;

/**
 * Created by juangarcia on 12/13/15.
 */
public class MeetingState {
    int confirmed;

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public MeetingTimeStateEnum getState(){
        return MeetingTimeStateEnum.findByCode(this.confirmed);
    }
}
