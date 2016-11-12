package com.offlinenews.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by idea on 12-11-2016.
 */
public class RestClient {
    private ApiServices mApiServices;

    OkHttpClient okHttpClient = new OkHttpClient();

    public RestClient() {
        RestAdapter restAdapter;
        RestAdapter.LogLevel logLevel;
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(EndPointsConfig.SERVER_ADDRESS)
                .setClient(new OkClient(okHttpClient))
                .build();
        mApiServices = restAdapter.create(ApiServices.class);
    }

    public ApiServices getApiServices() {
        return mApiServices;
    }

}
