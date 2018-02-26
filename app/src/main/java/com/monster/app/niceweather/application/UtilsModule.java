package com.monster.app.niceweather.application;

import android.content.Context;

import com.monster.app.niceweather.ext.Utils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by monster on 2/26/18.
 */
@Module(includes = ContextModule.class)
public class UtilsModule {

    @Provides
    @NiceWeatherAppScope
    public Utils utils(Context context) {
        return new Utils(context);
    }
}
