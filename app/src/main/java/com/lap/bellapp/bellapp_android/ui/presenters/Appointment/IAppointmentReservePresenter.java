package com.lap.bellapp.bellapp_android.ui.presenters.Appointment;

import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentReserveView;

/**
 * Created by juanpablogarcia on 1/18/16.
 */
public interface IAppointmentReservePresenter {
    public void setUpLoadedCompany(Company company);
    public void setUpLoadedService(BusinessService service);
    public void setUpLoadedTimeSlot(TimeSlot timeSlot);
    public void setUpStaff(StaffEntity setUpStaff);
    public Company getLoadedCompany();
    public BusinessService getBusinessService();
    public TimeSlot getTimeSlot();
    public StaffEntity getStaff();
    public void setUpView(AppointmentReserveView view);
    public void makeAppointment(MeetingTime meetingTime);
}
