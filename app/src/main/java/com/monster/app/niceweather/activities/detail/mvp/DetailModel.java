package com.monster.app.niceweather.activities.detail.mvp;

import android.app.Activity;

import com.monster.app.niceweather.activities.detail.DetailActivity;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ForecastModel;
import com.monster.app.niceweather.models.ListResultResponse;
import com.monster.app.niceweather.network.OpenweatherService;

import io.reactivex.Observable;

/**
 * Created by monster on 2/26/18.
 */

public class DetailModel {

    private final Activity activity;
    private final OpenweatherService service;
    private final ForecastCityModel cityModel;

    public DetailModel(Activity activity, OpenweatherService service) {
        this.activity = activity;
        this.service = service;
        cityModel = (ForecastCityModel) activity.getIntent().getSerializableExtra(DetailActivity.CITY_FORECAST);
    }

    public Observable<ListResultResponse<ForecastModel>> getForecast(int cityId, int count) {
        return service.getForecast(cityId, count);
    }

    public ForecastCityModel getForecastCity() {
        return cityModel;
    }

}
