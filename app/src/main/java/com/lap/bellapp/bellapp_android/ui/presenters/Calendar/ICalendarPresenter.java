package com.lap.bellapp.bellapp_android.ui.presenters.Calendar;

import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.ScheduleDay;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentsCalendarView;
import java.util.Date;
import java.util.List;

/**
 * Created by juanpablogarcia on 1/16/16.
 */
public interface ICalendarPresenter {
    public void setUpSelectedDate (Date date);
    public void setUpView (AppointmentsCalendarView view);
    public void setUpLoadedService(BusinessService service);
    public void setUpLoadedStaff(StaffEntity staff);
    public void setUpLoadedCompany(Company company);
    public Company getLoadedCompany();
    public BusinessService getBusinessService();
    public StaffEntity getStaff();
    public List<TimeSlot> calculateTimeSlots(List<ScheduleDay> schedules, List<MeetingTime> staffMeetings, int serviceDuration, Date selectedDay);
    public void loadServiceAvailableTimes();
    public void makeAppointment(int serviceId, int staffId, int companyId, int customerId, TimeSlot time);
}
