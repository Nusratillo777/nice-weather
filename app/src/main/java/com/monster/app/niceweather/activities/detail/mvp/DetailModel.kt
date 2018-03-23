package com.monster.app.niceweather.activities.detail.mvp

import android.app.Activity

import com.monster.app.niceweather.activities.detail.DetailActivity
import com.monster.app.niceweather.models.ForecastCityModel
import com.monster.app.niceweather.models.ForecastModel
import com.monster.app.niceweather.models.ListResultResponse
import com.monster.app.niceweather.network.OpenweatherService

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by monster on 2/26/18.
 */

class DetailModel(private val activity: Activity, private val service: OpenweatherService) {
    val forecastCity: ForecastCityModel

    init {
        forecastCity = activity.intent.getSerializableExtra(DetailActivity.CITY_FORECAST) as ForecastCityModel
    }

    fun getForecast(cityId: Int, count: Int): Observable<ListResultResponse<ForecastModel>> {
        return service.getForecast(cityId, count)
    }

}
