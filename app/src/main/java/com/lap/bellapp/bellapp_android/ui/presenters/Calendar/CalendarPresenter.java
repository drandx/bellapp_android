package com.lap.bellapp.bellapp_android.ui.presenters.Calendar;

import android.content.Context;
import android.util.Log;

import com.lap.bellapp.bellapp_android.data.DataManager;
import com.lap.bellapp.bellapp_android.data.model.BusinessHours;
import com.lap.bellapp.bellapp_android.data.model.BusinessService;
import com.lap.bellapp.bellapp_android.data.model.Company;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.ScheduleDay;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.reactive.executor.PostExecutionThread;
import com.lap.bellapp.bellapp_android.reactive.executor.ThreadExecutor;
import com.lap.bellapp.bellapp_android.ui.model.TimeSlot;
import com.lap.bellapp.bellapp_android.ui.view.AppointmentsCalendarView;
import com.lap.bellapp.bellapp_android.util.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by juanpablogarcia on 1/16/16.
 */
public class CalendarPresenter implements ICalendarPresenter {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final DataManager dataManager;
    private final Context mContext;

    private Subscription subscription = Subscriptions.empty();
    private AppointmentsCalendarView view;
    private StaffEntity loadedStaff;
    private BusinessService loadedService;
    private Company loadedCompany;
    private Date selectedDate;

    public CalendarPresenter(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Context mContext, DataManager dataManager) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.dataManager = dataManager;
        this.mContext = mContext;
    }

    @Override
    public void setUpSelectedDate(Date date) {
        this.selectedDate = date;
    }

    @Override
    public void setUpView(AppointmentsCalendarView view) {
        this.view = view;
    }

    @Override
    public void setUpLoadedService(BusinessService service) {
        this.loadedService = service;
    }

    @Override
    public void setUpLoadedStaff(StaffEntity staff) {
        this.loadedStaff = staff;
    }

    @Override
    public void setUpLoadedCompany(Company company) {
        this.loadedCompany = company;
    }

    @Override
    public List<TimeSlot> calculateTimeSlots(List<ScheduleDay> schedules, List<MeetingTime> staffMeetings, int serviceDuration, Date selectedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(selectedDay);
        int numberOfDay = c.get(Calendar.DAY_OF_WEEK);
        int index = (numberOfDay >= 2)?(numberOfDay - 2):(schedules.size() - 1);
        ScheduleDay availableShedule = schedules.get(index);
        boolean reachedEndOfDay = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDay);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(availableShedule.getStartTime().split(":")[0]));
        cal.set(Calendar.MINUTE,Integer.parseInt(availableShedule.getStartTime().split(":")[1]));
        Date dayInitTime = cal.getTime();
        cal.setTime(selectedDay);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(availableShedule.getEndTime().split(":")[0]));
        cal.set(Calendar.MINUTE,Integer.parseInt(availableShedule.getEndTime().split(":")[0]));
        Date dayEndTime = cal.getTime();
        List<TimeSlot> calculatedTimes = new LinkedList<>();
        int i = 0;
        while (!reachedEndOfDay){
            cal = Calendar.getInstance();
            Date initTimeSlot;
            Date finalTimeSlot;
            if (i>0){
                initTimeSlot = calculatedTimes.get(i-1).getFinalTime();
                cal.setTime(initTimeSlot);
                cal.add(Calendar.MINUTE, serviceDuration + Constants.SERVICE_DURATION_MINUTES);
                finalTimeSlot = cal.getTime();
            }
            else{initTimeSlot = dayInitTime;
                cal.setTime(dayInitTime);
                cal.add(Calendar.MINUTE, serviceDuration + Constants.SERVICE_DURATION_MINUTES);
                finalTimeSlot = cal.getTime();
            }
            //Validate if the initTimeSlot is in between any staffMeetings to mark that TimeSlot as buys
            TimeSlot timeSlot = new TimeSlot(initTimeSlot, finalTimeSlot, false);
            calculatedTimes.add(timeSlot);
            //Getting the last calculated hour
            cal.setTime(finalTimeSlot);
            int calculatedHour = cal.get(Calendar.HOUR_OF_DAY);
            cal.setTime(dayEndTime);
            int limitDayHour = cal.get(Calendar.HOUR_OF_DAY);
            if (calculatedHour > limitDayHour) {
                reachedEndOfDay = true;
            }
            i++;
        }
        return calculatedTimes;
    }

    @Override
    public void loadServiceAvailableTimes() {
        view.showLoadingView();
        subscription = dataManager.getBusinessHours(loadedCompany.companyId)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(new Subscriber<BusinessHours>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("CalendarPresenter",e.getMessage());
                    }

                    @Override
                    public void onNext(BusinessHours businessHours) {
                        List<TimeSlot> calculatedHours = calculateTimeSlots(businessHours.getSchedules(),
                                loadedStaff.getMeetingTimes(), loadedService.getMinutesDuration(), selectedDate);
                        view.loadStaffTimeSlots(calculatedHours);
                    }
                });
    }

    @Override
    public void makeAppointment(int serviceId, int staffId, int companyId, int customerId, TimeSlot time) {

    }
}
