package com.monster.app.niceweather.activities.detail.dagger

import android.app.Activity

import com.monster.app.niceweather.activities.detail.DetailActivity
import com.monster.app.niceweather.application.NiceWeatherAppComponent

import dagger.Component

/**
 * Created by monster on 2/26/18.
 */
@DetailScope
@Component(modules = [DetailModule::class], dependencies = [NiceWeatherAppComponent::class])
interface DetailComponent {
    fun inject(activity: DetailActivity)
}
