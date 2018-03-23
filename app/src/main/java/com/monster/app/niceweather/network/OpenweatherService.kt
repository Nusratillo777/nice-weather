package com.monster.app.niceweather.network

import com.monster.app.niceweather.models.ForecastCityModel
import com.monster.app.niceweather.models.ForecastModel
import com.monster.app.niceweather.models.ListResultResponse

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by monster on 2/24/18.
 */

/**
 * openweathermap api
 */
interface OpenweatherService {

    /**
     * getting forecasts for given area
     * @param bbox bounding box [lon-left,lat-bottom,lon-right,lat-top,zoom]
     * @return list of cities with current weather forecast
     */
    @GET("box/city")
    fun getCitiesForecast(@Query("bbox") bbox: String): Observable<ListResultResponse<ForecastCityModel>>

    /**
     * getting forecast for giving city
     * @param id the id of the city
     * @param cnt count of days
     * @return list of forecasts for given city
     */
    @GET("forecast/daily?units=metric")
    fun getForecast(@Query("id") id: Int, @Query("cnt") cnt: Int): Observable<ListResultResponse<ForecastModel>>
}
