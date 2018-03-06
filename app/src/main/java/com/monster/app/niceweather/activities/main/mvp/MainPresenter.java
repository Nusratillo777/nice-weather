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
                .flatMap(bbox -> mainModel.getCitiesForecast(bbox)
                        .map(response -> MainUiModel.stateSuccess(response.list))
                        .onErrorReturn(t -> MainUiModel.stateError(t.getMessage()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(MainUiModel.stateLoading()))
                .subscribe(this::handleResult);
    }

    private Disposable observeRefresh() {
        return mainView.observeRefresh()
                .map(__ -> BBOX)
                .observeOn(Schedulers.io())
                .flatMap(bbox -> mainModel.getCitiesForecast(bbox)
                        .map(response -> MainUiModel.stateSuccess(response.list))
                        .onErrorReturn(t -> MainUiModel.stateError(t.getMessage()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(MainUiModel.stateLoading()))
                .subscribe(this::handleResult);
    }

    public Disposable observeItemClick() {
        return mainView.observeItemClick()
                .subscribe(mainModel::startDetailActivity);
    }

    private void handleResult(MainUiModel model) {
        mainView.setLoading(model.isLoading);
        if (model.success)
            mainView.setForecastCityItems(model.data);
        else if (!model.isLoading)
            mainView.showError(model.error);
    }


    public void onDestroy() {
        compositeDisposable.clear();
    }

}
