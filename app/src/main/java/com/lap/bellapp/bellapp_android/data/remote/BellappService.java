package com.lap.bellapp.bellapp_android.data.remote;

import com.google.gson.JsonObject;
import com.lap.bellapp.bellapp_android.data.model.BusinessCategory;
import com.lap.bellapp.bellapp_android.data.model.BusinessHours;
import com.lap.bellapp.bellapp_android.data.model.CustomerEntity;
import com.lap.bellapp.bellapp_android.data.model.GeneralResult;
import com.lap.bellapp.bellapp_android.data.model.MeetingState;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;
import com.lap.bellapp.bellapp_android.ui.presenters.Appointment.StaffAppointmentListPresenter;

import java.util.List;
import java.util.Objects;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

public interface BellappService {

    @GET("staffsrest/{id}")
    Observable<StaffEntity> getStaff(@Path("id") int id);

    @GET("staffsrest/{email}/{password}")
    Observable<StaffEntity> getLoginStaff(@Path("email") String email, @Path("password") String password);

    @PUT("staffsrest/{id}")
    Observable<StaffEntity> putStaff(@Path("id") int id, @Body StaffEntity staffEntity);

    @GET("meetingtimesrest/{id}")
    Observable<MeetingTime> getAppointment(@Path("id") int id);

    @GET("staffsrest/meetingstatus/{id}")
    Observable<MeetingState> getMeetingState(@Path("id") int id);

    @POST("staffsrest/confirmmeeting/{id}")
    Observable<GeneralResult> confirmAppointment(@Path("id") int id, @Body JsonObject confirmObj);

    @GET("customersrest/{email}/{password}")
    Observable<CustomerEntity> getLoginCustomer(@Path("email") String email, @Path("password") String password);

    @GET("customersrest/{id}")
    Observable<CustomerEntity> getCustomer(@Path("id") int id);

    @PUT("customersrest/{id}")
    Observable<CustomerEntity> putCustomer(@Path("id") int id, @Body CustomerEntity customerEntity);

    @POST("customersrest")
    Observable<CustomerEntity> postCustomer(@Body CustomerEntity customerEntity);

    @GET("CustomersRest/termsconditions")
    Observable<String> getTermsConditions();

    @GET("businesscategoriesrest")
    Observable<List<BusinessCategory>> getBusinessCategories();

    @GET("staffsrest/service/{id}")
    Observable<List<StaffEntity>> getStaffsByService(@Path("id") int id);

    @GET("businesshours/{id}")
    Observable<BusinessHours> getBusinessHours(@Path("id") int id);

    @POST("meetingtimesrest")
    Observable<MeetingTime> postMeetingTime(@Body MeetingTime meetingTime);

}
