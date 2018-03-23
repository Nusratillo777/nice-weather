package com.monster.app.niceweather.activities.detail.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.monster.app.niceweather.R
import com.monster.app.niceweather.base.BaseView
import com.monster.app.niceweather.ext.Utils
import com.monster.app.niceweather.models.ForecastModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.view.*


/**
 * Created by monster on 2/26/18.
 */
@SuppressLint("ViewConstructor")
class DetailViewImpl(context: Context, private val picasso: Picasso, utils: Utils) : BaseView(context), DetailView {


    private val adapter: ForecastAdapter

    override val view = this

    init {
        inflate(R.layout.activity_detail)
        adapter = ForecastAdapter(picasso, utils)
        forecastList.adapter = adapter
    }

    override fun setMapImage(mapImageUrl: String) {
        picasso.load(mapImageUrl).into(mapImage)
    }

    override var observeRefresh = RxSwipeRefreshLayout.refreshes(refresh)

    override fun setLoading(loading: Boolean) {
        refresh.isRefreshing = loading
    }

    override fun setForecastItems(items: List<ForecastModel>) {
        adapter.swapData(items)
    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}
