package com.lap.bellapp.bellapp_android.data.remote;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lap.bellapp.bellapp_android.BellappApplication;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetrofitHelper {

    public BellappService newBellappService(Context context) {
        String dateConfig = ((BellappApplication)context.getApplicationContext()).getEnvDateParsing();
        Gson gson = new GsonBuilder().setDateFormat(dateConfig).create();

        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        Log.i("newBellappService",((BellappApplication)context.getApplicationContext()).getBaseUrl());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(((BellappApplication)context.getApplicationContext()).getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(BellappService.class);
    }

}
