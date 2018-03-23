package com.monster.app.niceweather.application

import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * Created by monster on 2/24/18.
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    @NiceWeatherAppScope
    fun context() = context
}
