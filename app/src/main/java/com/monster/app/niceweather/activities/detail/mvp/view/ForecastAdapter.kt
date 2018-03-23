package com.monster.app.niceweather.activities.detail.mvp.view

import android.content.Context
import android.view.View
import android.view.ViewGroup

import com.monster.app.niceweather.activities.main.mvp.view.ForecastCityItem
import com.monster.app.niceweather.base.BaseForecastAdapter
import com.monster.app.niceweather.ext.Utils
import com.monster.app.niceweather.models.ForecastModel
import com.squareup.picasso.Picasso

/**
 * Created by monster on 2/26/18.
 */

class ForecastAdapter(private val picasso: Picasso, private val utils: Utils) : BaseForecastAdapter<ForecastModel>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItem = when (convertView) {
            null -> ForecastItem(parent.context, picasso, utils)
            else -> convertView as ForecastItem
        }
        listItem.bindView(forecastList[position])
        return listItem
    }
}
