package com.monster.app.niceweather.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.monster.app.niceweather.network.OpenweatherService;
import com.squareup.picasso.Picasso;

/**
 * Created by monster on 2/24/18.
 */

public class NiceWeatherApp extends Application{

    private OpenweatherService openweatherService;
    Picasso picasso;
    NiceWeatherAppComponent appComponent;

    public static NiceWeatherApp get(Activity activity) {
        return (NiceWeatherApp) activity.getApplication();
    }

    public NiceWeatherAppComponent component() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerNiceWeatherAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        openweatherService = appComponent.getOpenweatherService();
        picasso = appComponent.getPicasso();
    }
}
