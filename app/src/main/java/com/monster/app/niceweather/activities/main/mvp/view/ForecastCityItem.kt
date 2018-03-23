package com.monster.app.niceweather.activities.main.mvp.view

import android.annotation.SuppressLint
import android.content.Context

import com.monster.app.niceweather.R
import com.monster.app.niceweather.base.BaseView
import com.monster.app.niceweather.models.ForecastCityModel
import com.squareup.picasso.Picasso

import com.monster.app.niceweather.ext.Constants.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_forecast_city.view.*


/**
 * Created by monster on 2/25/18.
 */
@SuppressLint("ViewConstructor")
class ForecastCityItem(context: Context, private val picasso: Picasso) : BaseView(context) {

    init {
        inflate(R.layout.item_forecast_city)
    }

    fun bindView(cityItem: ForecastCityModel) {
        cityName.text = cityItem.name
        weatherDescription.text = cityItem.weather[0].description
        forecastLow.text = cityItem.main.temp_min.toString()
        forecastHigh.text = cityItem.main.temp_max.toString()
        forecast.text = cityItem.main.temp.toString()
        picasso.load("$BASE_IMAGE_URL${cityItem.weather[0].icon}.png")
                .fit()
                .into(weatherIcon)
    }
}
