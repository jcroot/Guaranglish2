package com.arrobasoft.guaranglish.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.arrobasoft.guaranglish.BuildConfig;
import com.arrobasoft.guaranglish.GuaranglishApp;
import com.arrobasoft.guaranglish.networking.interfaces.APIService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by arrobasoft on 13/09/16.
 */
public class API {
    private static API instance;

    private APIService service;

    private API() {
        /* IGNORED */
    }

    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public APIService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient();
            client.networkInterceptors().add(new StethoInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }

    public static boolean isConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) GuaranglishApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }
}
