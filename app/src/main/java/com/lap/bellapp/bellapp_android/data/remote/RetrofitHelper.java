package com.lap.bellapp.bellapp_android.data.remote;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetrofitHelper {


    //TODO - Here I will need the context in order to access to BellappApplication and get BaseURL
    public BellappService newBellappService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BellappService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(BellappService.class);
    }

}
