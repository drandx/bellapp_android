package com.lap.bellapp.bellapp_android.data.remote;

import com.lap.bellapp.bellapp_android.data.model.User;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BellappService {

    String ENDPOINT = "https://ribots-api.ribot.io/";

    @GET("/staffsrest/{email}/{password}")
    Observable<User> authenticateStaffMember(@Path("email") String email, @Path("password") String password);

    @GET("/staffsrest/{id}/")
    Observable<User> getStaff(@Path("id") int id);
}
