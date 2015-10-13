package com.lap.bellapp.bellapp_android.data.remote;

import com.lap.bellapp.bellapp_android.data.model.Staff;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BellappService {

    String ENDPOINT = "http://bellappprod.azurewebsites.net/api/";

//    @GET("/staffsrest/{email}/{password}")
//    Observable<Staff> authenticateStaffMember(@Path("email") String email, @Path("password") String password);
//
    @GET("staffsrest/{id}")
    Observable<Staff> getStaff(@Path("id") int id);

    @GET("staffsrest/{id}")
    Call<Staff> getStaffSync(@Path("id") int id);
}
