package com.monster.app.niceweather.activities.main.mvp.view

import android.view.View

import com.monster.app.niceweather.models.ForecastCityModel

import io.reactivex.Observable

/**
 * Created by monster on 2/25/18.
 */

interface MainView {

    val view: View

    val observeRefresh: Observable<Any>

    val observeItemClick: Observable<ForecastCityModel>

    fun setLoading(loading: Boolean)

    fun setForecastCityItems(items: List<ForecastCityModel>)

    fun showError(message: String)

}
