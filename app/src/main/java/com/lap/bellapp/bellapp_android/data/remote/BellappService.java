package com.lap.bellapp.bellapp_android.data.remote;

import com.lap.bellapp.bellapp_android.data.entity.StaffEntity;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BellappService {

    @GET("staffsrest/{id}")
    Observable<StaffEntity> getStaff(@Path("id") int id);

    @GET("staffsrest/{email}/{password}")
    Observable<StaffEntity> getLoginStaff(@Path("email") String email, @Path("password") String password);

}
