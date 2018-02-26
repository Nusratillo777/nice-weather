package com.monster.app.niceweather.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by monster on 2/24/18.
 */
@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @NiceWeatherAppScope
    public Context context() {
        return context;
    }

}
