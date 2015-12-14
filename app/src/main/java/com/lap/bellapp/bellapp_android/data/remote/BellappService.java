package com.lap.bellapp.bellapp_android.data.remote;

import com.google.gson.JsonObject;
import com.lap.bellapp.bellapp_android.data.model.GeneralResult;
import com.lap.bellapp.bellapp_android.data.model.MeetingState;
import com.lap.bellapp.bellapp_android.data.model.MeetingTime;
import com.lap.bellapp.bellapp_android.data.model.StaffEntity;

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

}
