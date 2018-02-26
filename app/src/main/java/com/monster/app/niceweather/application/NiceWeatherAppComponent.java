package com.monster.app.niceweather.application;

import com.monster.app.niceweather.network.OpenweatherService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by monster on 2/24/18.
 */
@NiceWeatherAppScope
@Component(modules = {OpenweatherServiceModule.class, PicassoModule.class})
public interface NiceWeatherAppComponent {

    Picasso getPicasso();
    OpenweatherService getOpenweatherService();

}
