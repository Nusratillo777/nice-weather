package com.monster.app.niceweather.activities.main.mvp.view

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.monster.app.niceweather.base.BaseForecastAdapter
import com.monster.app.niceweather.models.ForecastCityModel
import com.squareup.picasso.Picasso

import java.util.ArrayList

class CitiesAdapter(private val picasso: Picasso) : BaseForecastAdapter<ForecastCityModel>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItem: ForecastCityItem = if (convertView == null)
            ForecastCityItem(parent.context, picasso)
         else
            convertView as ForecastCityItem
        listItem.bindView(forecastList[position])
        return listItem
    }
}
