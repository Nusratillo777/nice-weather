package com.monster.app.niceweather.activities.main.mvp

import android.util.Log
import android.widget.Toast

import com.monster.app.niceweather.activities.main.mvp.view.MainView
import com.monster.app.niceweather.models.ForecastCityModel
import com.monster.app.niceweather.models.ListResultResponse

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

import java.util.ArrayList
import java.util.Collections

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by monster on 2/25/18.
 */

class MainPresenter(private val mainView: MainView, private val mainModel: MainModel) {
    private val compositeDisposable = CompositeDisposable()
    private val BBOX = "67,39,70,42,10"

    fun onCreate() {
        compositeDisposable.add(refresh)
        compositeDisposable.add(observeRefresh)
        compositeDisposable.add(observeItemClick)
    }

    private val refresh = Observable.just(BBOX)
                .observeOn(Schedulers.io())
                .flatMap { bbox ->
                    mainModel.getCitiesForecast(bbox)
                            .map { response -> MainUiModel.stateSuccess(response.list) }
                            .onErrorReturn { t -> MainUiModel.stateError(t.message) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .startWith(MainUiModel.stateLoading())
                }
                .subscribe{ this.handleResult(it) }

    private val observeRefresh = mainView.observeRefresh
                .map { _ -> BBOX }
                .observeOn(Schedulers.io())
                .flatMap { bbox ->
                    mainModel.getCitiesForecast(bbox)
                            .map { response -> MainUiModel.stateSuccess(response.list) }
                            .onErrorReturn { t -> MainUiModel.stateError(t.message) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .startWith(MainUiModel.stateLoading())
                }
                .subscribe{ this.handleResult(it) }

    private val observeItemClick = mainView.observeItemClick
                .subscribe{ mainModel.startDetailActivity(it) }

    private fun handleResult(model: MainUiModel) {
        mainView.setLoading(model.isLoading)
        if (model.success)
            mainView.setForecastCityItems(model.data!!)
        else if (!model.isLoading)
            mainView.showError(model.error)
    }

    fun onDestroy() = compositeDisposable.clear()

}
