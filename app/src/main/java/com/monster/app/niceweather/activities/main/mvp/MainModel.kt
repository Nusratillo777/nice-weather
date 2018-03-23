package com.monster.app.niceweather.activities.main.mvp

import android.app.Activity
import android.widget.Toast

import com.monster.app.niceweather.activities.detail.DetailActivity
import com.monster.app.niceweather.models.ForecastCityModel
import com.monster.app.niceweather.models.ListResultResponse
import com.monster.app.niceweather.network.OpenweatherService

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by monster on 2/25/18.
 */

class MainModel(private val activity: Activity, private val service: OpenweatherService) {

    fun getCitiesForecast(BBOX: String) = service.getCitiesForecast(BBOX)

    fun startDetailActivity(city: ForecastCityModel) = DetailActivity.start(activity, city)
}
