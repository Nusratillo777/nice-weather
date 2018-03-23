package com.monster.app.niceweather.activities.main.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ListAdapter
import android.widget.Toast

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.monster.app.niceweather.R
import com.monster.app.niceweather.base.BaseView
import com.monster.app.niceweather.models.ForecastCityModel
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by monster on 2/25/18.
 */

@SuppressLint("ViewConstructor")
class MainViewImpl(context: Context, picasso: Picasso) : BaseView(context), MainView {

    private val adapter: CitiesAdapter

    init {
        inflate(R.layout.activity_main)
        adapter = CitiesAdapter(picasso)
        forecastList.adapter = adapter
    }

    override val observeRefresh = RxSwipeRefreshLayout.refreshes(refresh)

    override val observeItemClick = RxAdapterView.itemClicks<ListAdapter>(forecastList)
            .map { position -> adapter.getItem(position) }!!

    override fun setLoading(loading: Boolean) {
        refresh.isRefreshing = loading
    }

    override fun setForecastCityItems(items: List<ForecastCityModel>) {
        adapter.swapData(items)
    }

    override val view = this

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
