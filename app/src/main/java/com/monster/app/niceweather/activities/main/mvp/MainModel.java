package com.monster.app.niceweather.activities.main.mvp;

import android.app.Activity;
import android.widget.Toast;

import com.monster.app.niceweather.activities.detail.DetailActivity;
import com.monster.app.niceweather.models.ForecastCityModel;
import com.monster.app.niceweather.models.ListResultResponse;
import com.monster.app.niceweather.network.OpenweatherService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by monster on 2/25/18.
 */

public class MainModel {

    private OpenweatherService service;
    private Activity activity;

    public MainModel(Activity activity, OpenweatherService service) {
         this.activity = activity;
         this.service = service;
    }

    public Observable<ListResultResponse<ForecastCityModel>> getCitiesForecast(String BBOX) {
        return service.getCitiesForecast(BBOX);
    }

    public void startDetailActivity(ForecastCityModel city) {
        DetailActivity.start(activity, city);
    }

}
