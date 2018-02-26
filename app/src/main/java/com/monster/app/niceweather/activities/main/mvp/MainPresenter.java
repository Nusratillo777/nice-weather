package com.monster.app.niceweather.activities.main.mvp;

import com.monster.app.niceweather.activities.main.mvp.view.MainView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                .map(citiesForecast -> citiesForecast.list)
                .subscribe(mainView::setForecastCityItems);
    }

    private Disposable observeRefresh() {
        return mainView.observeRefresh()
                .doOnNext(__ -> mainView.setLoading(true))
                .observeOn(Schedulers.io())
                .map(__ -> BBOX)
                .switchMap(mainModel::getCitiesForecast)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(__ -> mainView.setLoading(false))
                .map(citiesForecast -> citiesForecast.list)
                .subscribe(mainView::setForecastCityItems);
    }

    public Disposable observeItemClick() {
        return mainView.observeItemClick()
                .subscribe(mainModel::startDetailActivity);
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

}
