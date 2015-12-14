package com.lap.bellapp.bellapp_android.data.model;

import android.content.Context;

import com.lap.bellapp.bellapp_android.R;

/**
 * Created by juangarcia on 12/13/15.
 */
public enum MeetingTimeStateEnum {
    PENDING_CONFIRMATION  (0),
    REJECTED(1),
    ACCEPTED(2);

    private final int stateCode;

    private MeetingTimeStateEnum(int stateCode) {
        this.stateCode = stateCode;
    }

    public int getStateCode(){
        return stateCode;
    }

    public static MeetingTimeStateEnum findByCode(int code){
        for(MeetingTimeStateEnum v : values()){
            if( v.getStateCode() == code){
                return v;
            }
        }
        return null;
    }

    public String getDescription(Context context){
        switch (this){
            case PENDING_CONFIRMATION:
                return context.getResources().getString(R.string.meeting_state_pending_confirmation);
            case REJECTED:
                return context.getResources().getString(R.string.meeting_state_rejected);
            case ACCEPTED:
                return context.getResources().getString(R.string.meeting_state_confirmed);
        }
        return "";
    }

}
