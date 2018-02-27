package com.monster.app.niceweather.activities.main.mvp;

import android.widget.Toast;

import com.monster.app.niceweather.activities.main.mvp.view.MainView;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ListResultResponse;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by monster on 2/25/18.
 */

public class MainPresenter {
    private static final String BBOX = "67,39,70,42,10";
    private final MainView mainView;
    private final MainModel mainModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(MainView mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate() {
        compositeDisposable.add(refresh());
        compositeDisposable.add(observeRefresh());
        compositeDisposable.add(observeItemClick());
    }

    private Disposable refresh() {
        return Observable.just(BBOX)
                .observeOn(Schedulers.io())
                .switchMap(mainModel::getCitiesForecast)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, error -> mainView.showError());
    }

    private Disposable observeRefresh() {
        return mainView.observeRefresh()
                .doOnNext(__ -> mainView.setLoading(true))
                .observeOn(Schedulers.io())
                .map(__ -> BBOX)
                .switchMap(mainModel::getCitiesForecast)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(__ -> mainView.setLoading(false))
                .retry()
                .subscribe(this::handleResult,
                        error -> mainView.showError());
    }

    public Disposable observeItemClick() {
        return mainView.observeItemClick()
                .subscribe(mainModel::startDetailActivity);
    }

    private void handleResult(Response<ListResultResponse<ForecastCityModel>> resp) {
        if(resp.isSuccessful())
            mainView.setForecastCityItems(resp.body().list);
        else
            mainView.showError();
    }


    public void onDestroy() {
        compositeDisposable.clear();
    }

}
