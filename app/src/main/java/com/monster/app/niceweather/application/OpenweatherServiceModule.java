package com.monster.app.niceweather.application;

import com.monster.app.niceweather.ext.Constants;
import com.monster.app.niceweather.network.OpenweatherService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by monster on 2/24/18.
 */

@Module(includes = NetworkModule.class)
public class OpenweatherServiceModule {

    @Provides
    @NiceWeatherAppScope
    public OpenweatherService openweatherService(Retrofit client) {
        return client.create(OpenweatherService.class);
    }

    @Provides
    @NiceWeatherAppScope
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
