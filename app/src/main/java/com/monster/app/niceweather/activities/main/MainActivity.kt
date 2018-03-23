package com.monster.app.niceweather.activities.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.monster.app.niceweather.activities.main.dagger.DaggerMainComponent

import com.monster.app.niceweather.activities.main.dagger.MainModule
import com.monster.app.niceweather.activities.main.mvp.MainPresenter
import com.monster.app.niceweather.activities.main.mvp.view.MainView
import com.monster.app.niceweather.application.NiceWeatherApp

import javax.inject.Inject

/**
 * Created by monster on 2/24/18.
 */

class MainActivity : AppCompatActivity() {

    var view: MainView? = null
    @Inject set

    var mainPresenter: MainPresenter? = null
    @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainComponent.builder()
                .niceWeatherAppComponent(NiceWeatherApp.get(this).component())
                .mainModule(MainModule(this))
                .build().inject(this)
        mainPresenter!!.onCreate()
        setContentView(view!!.view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter!!.onDestroy()
    }
}
