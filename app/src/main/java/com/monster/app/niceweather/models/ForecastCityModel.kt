package com.monster.app.niceweather.models

import java.io.Serializable

/**
 * Created by monster on 2/24/18.
 */

class ForecastCityModel(val id: Int,
                        val name: String,
                        val coord: CoordModel,
                        val main: TempModel,
                        val weather: List<WeatherModel>) : Serializable {

    class CoordModel(val Lat: Double, val Lon: Double) : Serializable

    class TempModel(val temp: Double, val temp_min: Double, val temp_max: Double) : Serializable

    class WeatherModel(val description: String, val icon: String) : Serializable
}
