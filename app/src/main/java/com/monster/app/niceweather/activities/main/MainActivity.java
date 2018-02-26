package com.monster.app.niceweather.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.monster.app.niceweather.activities.main.dagger.DaggerMainComponent;
import com.monster.app.niceweather.activities.main.dagger.MainComponent;
import com.monster.app.niceweather.activities.main.dagger.MainModule;
import com.monster.app.niceweather.activities.main.mvp.MainPresenter;
import com.monster.app.niceweather.activities.main.mvp.view.MainView;
import com.monster.app.niceweather.application.NiceWeatherApp;

import javax.inject.Inject;

/**
 * Created by monster on 2/24/18.
 */

public class MainActivity extends AppCompatActivity {

    @Inject
    MainView view;

    @Inject
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder()
                .niceWeatherAppComponent(NiceWeatherApp.get(this).component())
                .mainModule(new MainModule(this))
                .build().inject(this);

        setContentView(view.getView());
        mainPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
