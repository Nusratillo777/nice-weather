package com.monster.app.niceweather.activities.detail.dagger;

import android.app.Activity;

import com.monster.app.niceweather.activities.detail.mvp.DetailModel;
import com.monster.app.niceweather.activities.detail.mvp.DetailPresenter;
import com.monster.app.niceweather.activities.detail.mvp.view.DetailView;
import com.monster.app.niceweather.activities.detail.mvp.view.DetailViewImpl;
import com.monster.app.niceweather.ext.Utils;
import com.monster.app.niceweather.network.OpenweatherService;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by monster on 2/26/18.
 */
@Module
public class DetailModule {

    private final Activity activity;

    public DetailModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @DetailScope
    public DetailModel detailModel(OpenweatherService service) {
        return new DetailModel(activity, service);
    }

    @Provides
    @DetailScope
    public DetailView detailView(Picasso picasso, Utils utils) {
        return new DetailViewImpl(activity, picasso, utils);
    }

    @Provides
    @DetailScope
    public DetailPresenter detailPresenter(DetailView detailView, DetailModel detailModel, Utils utils) {
        return new DetailPresenter(detailView, detailModel, utils);
    }
}
