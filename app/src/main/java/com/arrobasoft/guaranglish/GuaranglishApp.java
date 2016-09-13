package com.arrobasoft.guaranglish;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by arrobasoft on 13/09/16.
 */
public class GuaranglishApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext() {
        return mContext;
    }

    private SharedPreferences preferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}
