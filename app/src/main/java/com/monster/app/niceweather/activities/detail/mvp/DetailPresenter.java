package com.monster.app.niceweather.activities.detail.mvp;

import com.monster.app.niceweather.activities.detail.mvp.view.DetailView;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.models.ForecastCityModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.monster.app.niceweather.BuildConfig.GOOGLE_API_KEY;

/**
 * Created by monster on 2/26/18.
 */

public class DetailPresenter {
    private static final int FORECAST_COUNT = 14;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DetailModel detailModel;
    private DetailView detailView;
    private Utils utils;

    public DetailPresenter(DetailView detailView, DetailModel detailModel, Utils utils) {
        this.detailModel = detailModel;
        this.detailView = detailView;
        this.utils = utils;
    }

    public void onCreate() {
        compositeDisposable.add(refresh());
        compositeDisposable.add(observeRefresh());
        detailView.setTitle(detailModel.getForecastCity().name);
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

    private Disposable refresh() {
        return Observable.just(detailModel.getForecastCity())
                .observeOn(Schedulers.io())
                .flatMap(city -> detailModel.getForecast(city.id, FORECAST_COUNT)
                        .map(response -> DetailUiModel.stateSuccess(response.list, detailModel.getForecastCity()))
                        .onErrorReturn(t -> DetailUiModel.stateError(t.getMessage()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(DetailUiModel.stateLoading()))
                .subscribe(this::handleResponse);
    }


    private Disposable observeRefresh() {
        return detailView.observeRefresh()
                .observeOn(Schedulers.io())
                .map(__ -> detailModel.getForecastCity())
                .flatMap(city -> detailModel.getForecast(city.id, FORECAST_COUNT)
                        .map(response -> DetailUiModel.stateSuccess(response.list, detailModel.getForecastCity()))
                        .onErrorReturn(t -> DetailUiModel.stateError(t.getMessage()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(DetailUiModel.stateLoading()))
                .subscribe(this::handleResponse);
    }


    private void handleResponse(DetailUiModel model) {
        detailView.setLoading(model.isLoading);
        if (model.success)
            detailView.setForecastItems(model.data);
        else if (!model.isLoading)
            detailView.showError(model.error);
        if (model.city != null)
            detailView.setMapImage(createMapUrl(model.city));
    }


    private String createMapUrl(ForecastCityModel city) {
        return new StringBuilder("").append("https://maps.googleapis.com/maps/api/staticmap?")
                .append("center=").append(city.coord.Lat)
                .append(",").append(city.coord.Lon)
                .append("&markers=").append(city.coord.Lat)
                .append(",").append(city.coord.Lon)
                .append("&zoom=14")
                .append("&size=").append(utils.getScreenWidth())
                .append("x").append(utils.getScreenHeight() / 3)
                .append("&sensor=false").append("&key=").append(GOOGLE_API_KEY)
                .toString();
    }
}
