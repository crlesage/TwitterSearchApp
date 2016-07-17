package com.crlesage.twittersearchapp.utils;

import com.crlesage.twittersearchapp.interfaces.ApiInterface;
import com.crlesage.twittersearchapp.interfaces.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chris on 7/11/2016.
 * A service to call Twitter REST Api's via retrofit.
 */
public class ApiService {

    private ApiInterface mApiInterface;

    public ApiService() {

        //Logging client for Retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //Instance of Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TWITTER_SEARCH_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApi() {
        return mApiInterface;
    }
}
