package com.monster.app.niceweather.activities.detail.mvp;

import com.monster.app.niceweather.activities.detail.mvp.view.DetailView;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;
import com.monster.app.niceweather.models.ListResultResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
                .doOnNext(city -> detailView.setMapImage(createMapUrl(city)))
                .observeOn(Schedulers.io())
                .switchMap(city -> detailModel.getForecast(city.id, FORECAST_COUNT))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, error->detailView.showError());
    }


    private Disposable observeRefresh() {
        return detailView.observeRefresh()
                .doOnNext(__ -> detailView.setLoading(true))
                .observeOn(Schedulers.io())
                .map(__ -> detailModel.getForecastCity())
                .switchMap(city -> detailModel.getForecast(city.id, FORECAST_COUNT))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(__ -> detailView.setLoading(false))
                .retry()
                .subscribe(this::handleResponse, error->detailView.showError());
    }


    private void handleResponse(Response<ListResultResponse<ForecastModel>> response) {
        if(response.isSuccessful())
            detailView.setForecastItems(response.body().list);
        else
            detailView.showError();
    }


    private String createMapUrl(ForecastCityModel city) {
        return new StringBuilder("").append("https://maps.googleapis.com/maps/api/staticmap?")
                .append("center=").append(city.coord.Lat)
                .append(",").append(city.coord.Lon)
                .append("&markers=").append(city.coord.Lat)
                .append(",").append(city.coord.Lon)
                .append("&zoom=14")
                .append("&size=").append(utils.getScreenWidth())
                .append("x").append(utils.getScreenHeight()/3)
                .append("&sensor=false").append("&key=").append(GOOGLE_API_KEY)
                .toString();

    }
}
