package com.lap.bellapp.bellapp_android.data.remote;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lap.bellapp.bellapp_android.BellappApplication;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetrofitHelper {


    //TODO - Here I will need the context in order to access to BellappApplication and get BaseURL
    public BellappService newBellappService(Context context) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Log.i("newBellappService",((BellappApplication)context.getApplicationContext()).getBaseUrl());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(((BellappApplication)context.getApplicationContext()).getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(BellappService.class);
    }

}
