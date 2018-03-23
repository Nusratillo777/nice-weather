package com.monster.app.niceweather.activities.main.dagger

import com.monster.app.niceweather.activities.main.MainActivity
import com.monster.app.niceweather.application.NiceWeatherAppComponent

import dagger.Component

/**
 * Created by monster on 2/25/18.
 */
@MainScope
@Component(modules = [MainModule::class], dependencies = [NiceWeatherAppComponent::class])
interface MainComponent {

    fun inject(activity: MainActivity)

}
