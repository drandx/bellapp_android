package com.lap.bellapp.bellapp_android.data.model;

import java.util.List;

/**
 * Created by juanpablogarcia on 1/16/16.
 */
public class BusinessHours {
    public List<ScheduleDay> schedules;

    public List<ScheduleDay> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDay> schedules) {
        this.schedules = schedules;
    }
}
