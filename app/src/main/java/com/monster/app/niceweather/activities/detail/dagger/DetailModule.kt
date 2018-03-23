package com.monster.app.niceweather.activities.detail.dagger

import android.app.Activity

import com.monster.app.niceweather.activities.detail.mvp.DetailModel
import com.monster.app.niceweather.activities.detail.mvp.DetailPresenter
import com.monster.app.niceweather.activities.detail.mvp.view.DetailView
import com.monster.app.niceweather.activities.detail.mvp.view.DetailViewImpl
import com.monster.app.niceweather.ext.Utils
import com.monster.app.niceweather.network.OpenweatherService
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides

/**
 * Created by monster on 2/26/18.
 */
@Module
class DetailModule(private val activity: Activity) {

    @Provides
    @DetailScope
    fun detailModel(service: OpenweatherService) = DetailModel(activity, service)

    @Provides
    @DetailScope
    fun detailView(picasso: Picasso, utils: Utils): DetailView = DetailViewImpl(activity, picasso, utils)

    @Provides
    @DetailScope
    fun detailPresenter(detailView: DetailView, detailModel: DetailModel, utils: Utils) = DetailPresenter(detailView, detailModel, utils)
}
