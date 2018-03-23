package com.monster.app.niceweather.activities.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.monster.app.niceweather.activities.detail.dagger.DaggerDetailComponent
import com.monster.app.niceweather.activities.detail.dagger.DetailModule
import com.monster.app.niceweather.activities.detail.mvp.DetailPresenter
import com.monster.app.niceweather.activities.detail.mvp.view.DetailView
import com.monster.app.niceweather.application.NiceWeatherApp
import com.monster.app.niceweather.models.ForecastCityModel

import javax.inject.Inject

/**
 * Created by monster on 2/26/18.
 */

class DetailActivity : AppCompatActivity() {

    var detailView: DetailView? = null
    @Inject set

    var detailPresenter: DetailPresenter? = null
    @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDetailComponent.builder()
                .niceWeatherAppComponent(NiceWeatherApp.get(this).component())
                .detailModule(DetailModule(this))
                .build().inject(this)
        setContentView(detailView!!.view)
        detailPresenter!!.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter!!.onDestroy()
    }

    companion object {

        const val CITY_FORECAST = "city"

        fun start(context: Context, model: ForecastCityModel) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CITY_FORECAST, model)
            context.startActivity(intent)
        }
    }
}
