package com.monster.app.niceweather.activities.main.mvp.view;

import android.view.View;

import com.monster.app.niceweather.models.ForecastCityModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by monster on 2/25/18.
 */

public interface MainView {

    View getView();

    Observable<Object> observeRefresh();

    Observable<Integer> observeItemClick();

    void setLoading(boolean loading);

    void setForecastCityItems(List<ForecastCityModel> items);

}
