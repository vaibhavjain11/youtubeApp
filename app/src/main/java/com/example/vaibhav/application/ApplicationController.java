package com.example.vaibhav.application;

import android.app.Application;

import com.example.vaibhav.Utility.Constants;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Vaibhav on 7/14/2015.
 */
public class ApplicationController extends Application {

    private static RestAdapter restAdapter;

    public static RestAdapter getRestAdapter(){
        if(restAdapter == null){
            synchronized (ApplicationController.class){
                if(restAdapter == null){
                    setRestAdapter();
                }
            }
        }
        return restAdapter;
    }

    public static RestAdapter setRestAdapter(){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("App-Name" , "You tube");
            }
        };

        restAdapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL).setClient(new OkClient(new OkHttpClient())).build();
        return  restAdapter;
    }

}
