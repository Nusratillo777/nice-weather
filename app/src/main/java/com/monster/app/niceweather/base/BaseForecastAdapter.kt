package com.monster.app.niceweather.base

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.monster.app.niceweather.activities.main.mvp.view.ForecastCityItem
import com.monster.app.niceweather.models.ForecastCityModel

import java.util.ArrayList

/**
 * Created by monster on 2/26/18.
 */

abstract class BaseForecastAdapter<T> : BaseAdapter() {

    protected val forecastList: MutableList<T> = ArrayList(0)

    override fun getCount() = forecastList.size

    override fun getItem(position: Int) = forecastList[position]


    override fun getItemId(position: Int) = forecastList[position]!!.hashCode().toLong()

    fun swapData(repoList: List<T>?) {
        forecastList.clear()
        repoList?.let{forecastList.addAll(it)}
        notifyDataSetChanged()
    }
}
