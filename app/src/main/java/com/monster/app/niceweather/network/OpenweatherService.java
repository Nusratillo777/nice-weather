package com.monster.app.niceweather.network;

import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;
import com.monster.app.niceweather.models.ListResultResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by monster on 2/24/18.
 */

/**
 * openweathermap api
 */
public interface OpenweatherService {

    /**
     * getting forecasts for given area
     * @param bbox bounding box [lon-left,lat-bottom,lon-right,lat-top,zoom]
     * @return list of cities with current weather forecast
     */
    @GET("box/city")
    Observable<ListResultResponse<ForecastCityModel>> getCitiesForecast(@Query("bbox") String bbox);

    /**
     * getting forecast for giving city
     * @param id the id of the city
     * @param cnt count of days
     * @return list of forecasts for given city
     */
    @GET("forecast/daily")
    Observable<ListResultResponse<ForecastModel>> getForecast(@Query("id") int id, @Query("cnt") int cnt);
}
