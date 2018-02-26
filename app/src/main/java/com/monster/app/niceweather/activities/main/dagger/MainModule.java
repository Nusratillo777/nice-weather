package com.monster.app.niceweather.activities.main.dagger;

import android.app.Activity;

import com.monster.app.niceweather.activities.main.MainActivity;
import com.monster.app.niceweather.activities.main.mvp.MainModel;
import com.monster.app.niceweather.activities.main.mvp.MainPresenter;
import com.monster.app.niceweather.activities.main.mvp.view.CitiesAdapter;
import com.monster.app.niceweather.activities.main.mvp.view.MainView;
import com.monster.app.niceweather.activities.main.mvp.view.MainViewImpl;
import com.monster.app.niceweather.network.OpenweatherService;
import com.squareup.picasso.Picasso;

import java.lang.reflect.AccessibleObject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by monster on 2/25/18.
 */
@Module
public class MainModule {

    private final Activity activity;

    public MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    public MainView view(Picasso picasso) {
        return new MainViewImpl(activity, picasso);
    }

    @Provides
    @MainScope
    public MainPresenter presenter(MainView view, MainModel model) {
        return new MainPresenter(view, model);
    }

    @Provides
    @MainScope
    public MainModel model(OpenweatherService service) {
        return new MainModel(activity, service);
    }

}
