package com.monster.app.niceweather.activities.detail.mvp.view;

import android.view.View;

import com.monster.app.niceweather.models.ForecastModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by monster on 2/26/18.
 */

public interface DetailView {

    View getView();

    void setMapImage(String mapImageUrl);

    Observable<Object> observeRefresh();

    void setLoading(boolean loading);

    void setForecastItems(List<ForecastModel> items);

    void setTitle(String title);

}
