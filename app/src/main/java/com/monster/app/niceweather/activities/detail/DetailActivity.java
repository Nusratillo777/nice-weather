package com.monster.app.niceweather.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.monster.app.niceweather.activities.detail.dagger.DaggerDetailComponent;
import com.monster.app.niceweather.activities.detail.dagger.DetailModule;
import com.monster.app.niceweather.activities.detail.mvp.DetailPresenter;
import com.monster.app.niceweather.activities.detail.mvp.view.DetailView;
import com.monster.app.niceweather.application.NiceWeatherApp;
import com.monster.app.niceweather.models.ForecastCityModel;

import javax.inject.Inject;

/**
 * Created by monster on 2/26/18.
 */

public class DetailActivity extends AppCompatActivity {

    @Inject
    DetailView detailView;

    @Inject
    DetailPresenter detailPresenter;


    public static final String CITY_FORECAST = "city";

    public static void start(Context context, ForecastCityModel model) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(CITY_FORECAST, model);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDetailComponent.builder()
                .niceWeatherAppComponent(NiceWeatherApp.get(this).component())
                .detailModule(new DetailModule(this))
                .build().inject(this);
        setContentView(detailView.getView());
        detailPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.onDestroy();
    }
}
