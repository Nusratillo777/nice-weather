package com.monster.app.niceweather.activities.detail.mvp

import com.monster.app.niceweather.activities.detail.mvp.view.DetailView
import com.monster.app.niceweather.ext.Utils
import com.monster.app.niceweather.models.ForecastCityModel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import com.monster.app.niceweather.BuildConfig.GOOGLE_API_KEY

/**
 * Created by monster on 2/26/18.
 */

class DetailPresenter(private val detailView: DetailView, private val detailModel: DetailModel, private val utils: Utils) {
    private val compositeDisposable = CompositeDisposable()

    fun onCreate() {
        compositeDisposable.add(refresh)
        compositeDisposable.add(observeRefresh)
        detailView.setTitle(detailModel.forecastCity.name)
    }

    fun onDestroy() = compositeDisposable.clear()

    private val refresh = Observable.just(detailModel.forecastCity)
            .observeOn(Schedulers.io())
            .flatMap { city ->
                detailModel.getForecast(city.id, FORECAST_COUNT)
                        .map { response -> DetailUiModel.stateSuccess(response.list, detailModel.forecastCity) }
                        .onErrorReturn { t -> DetailUiModel.stateError(t.message) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(DetailUiModel.stateLoading)
            }
            .subscribe { this.handleResponse(it) }


    private val observeRefresh = detailView.observeRefresh
            .observeOn(Schedulers.io())
            .map { _ -> detailModel.forecastCity }
            .flatMap { city ->
                detailModel.getForecast(city.id, FORECAST_COUNT)
                        .map { response -> DetailUiModel.stateSuccess(response.list, detailModel.forecastCity) }
                        .onErrorReturn { t -> DetailUiModel.stateError(t.message) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(DetailUiModel.stateLoading)
            }
            .subscribe { this.handleResponse(it) }


    private fun handleResponse(model: DetailUiModel) {
        detailView.setLoading(model.isLoading)
        when {
            model.success -> {
                detailView.setForecastItems(model.data!!)
                detailView.setMapImage(createMapUrl(model.city!!))
            }
            !model.isLoading -> detailView.showError(model.error)
            else -> return
        }
    }


    private fun createMapUrl(city: ForecastCityModel) = """https://maps.googleapis.com/maps/api/staticmap?
        |center=${city.coord.Lat},${city.coord.Lon}
        |&markers=${city.coord.Lat},${city.coord.Lon}
        |&zoom=14&size=${utils.screenWidth}x${utils.screenHeight}
        |&sensor=false&key=$GOOGLE_API_KEY
    """.trimMargin()

    companion object {
        const val FORECAST_COUNT = 14
    }
}
