package com.monster.app.niceweather.activities.main.dagger

import android.app.Activity

import com.monster.app.niceweather.activities.main.MainActivity
import com.monster.app.niceweather.activities.main.mvp.MainModel
import com.monster.app.niceweather.activities.main.mvp.MainPresenter
import com.monster.app.niceweather.activities.main.mvp.view.CitiesAdapter
import com.monster.app.niceweather.activities.main.mvp.view.MainView
import com.monster.app.niceweather.activities.main.mvp.view.MainViewImpl
import com.monster.app.niceweather.network.OpenweatherService
import com.squareup.picasso.Picasso

import java.lang.reflect.AccessibleObject

import dagger.Module
import dagger.Provides

/**
 * Created by monster on 2/25/18.
 */
@Module
class MainModule(private val activity: Activity) {

    @Provides
    @MainScope
    fun view(picasso: Picasso):MainView =  MainViewImpl(activity, picasso)

    @Provides
    @MainScope
    fun presenter(view: MainView, model: MainModel) = MainPresenter(view, model)

    @Provides
    @MainScope
    fun model(service: OpenweatherService) = MainModel(activity, service)

}
