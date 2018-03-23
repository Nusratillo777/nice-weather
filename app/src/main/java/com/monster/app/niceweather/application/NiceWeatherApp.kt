package com.monster.app.niceweather.application

import android.app.Activity
import android.app.Application
import android.content.Context

import com.monster.app.niceweather.network.OpenweatherService
import com.squareup.picasso.Picasso

/**
 * Created by monster on 2/24/18.
 */

class NiceWeatherApp : Application() {

    private var appComponent: NiceWeatherAppComponent? = null

    fun component(): NiceWeatherAppComponent {
        return appComponent!!
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerNiceWeatherAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {

        operator fun get(activity: Activity): NiceWeatherApp {
            return activity.application as NiceWeatherApp
        }
    }
}
