package com.monster.app.niceweather.application

import android.content.Context

import com.monster.app.niceweather.ext.Utils

import dagger.Module
import dagger.Provides

/**
 * Created by monster on 2/26/18.
 */
@Module(includes = [ContextModule::class])
class UtilsModule {
    @Provides
    @NiceWeatherAppScope
    fun utils(context: Context) = Utils(context)
}
