package com.monster.app.niceweather.activities.detail.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView

import com.monster.app.niceweather.R
import com.monster.app.niceweather.base.BaseView
import com.monster.app.niceweather.ext.Utils
import com.monster.app.niceweather.models.ForecastModel
import com.squareup.picasso.Picasso


import com.monster.app.niceweather.ext.Constants.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_forecast.view.*


/**
 * Created by monster on 2/26/18.
 */
@SuppressLint("ViewConstructor")
class ForecastItem(context: Context, private val picasso: Picasso, private val utils: Utils) : BaseView(context) {

    init {
        inflate(R.layout.item_forecast)
    }

    fun bindView(forecastModel: ForecastModel) {
        weatherDescription.text = forecastModel.weather[0].description
        forecastLow.text = forecastModel.temp.min.toString()
        forecastHigh.text = forecastModel.temp.max.toString()
        forecast.text = utils.getRightTemp(forecastModel.temp).toString()
        picasso.load("$BASE_IMAGE_URL${forecastModel.weather[0].icon}.png")
                .fit()
                .centerCrop()
                .into(weatherIcon)
    }
}
