package com.monster.app.niceweather.activities.detail.mvp.view

import android.view.View

import com.monster.app.niceweather.models.ForecastModel

import io.reactivex.Observable

/**
 * Created by monster on 2/26/18.
 */

interface DetailView {

    val view: View

    fun setMapImage(mapImageUrl: String)

    val observeRefresh: Observable<Any>

    fun setLoading(loading: Boolean)

    fun setForecastItems(items: List<ForecastModel>)

    fun setTitle(title: String)

    fun showError(error: String)

}
