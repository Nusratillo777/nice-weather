package com.monster.app.niceweather.models

/**
 * Created by monster on 2/24/18.
 */

class ForecastModel(val temp: TempModel, val weather: List<ForecastCityModel.WeatherModel>) {

    class TempModel (
        val day:Double = 0.0,
        val min:Double = 0.0,
        val max:Double = 0.0,
        val night:Double = 0.0,
        val eve:Double = 0.0,
        val morn:Double = 0.0
    )
}
