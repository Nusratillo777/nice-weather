package com.monster.app.niceweather.application

import com.monster.app.niceweather.ext.Constants
import com.monster.app.niceweather.network.OpenweatherService

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by monster on 2/24/18.
 */

@Module(includes = [NetworkModule::class])
class OpenweatherServiceModule {

    @Provides
    @NiceWeatherAppScope
    fun openweatherService(client: Retrofit): OpenweatherService {
        return client.create(OpenweatherService::class.java)
    }

    @Provides
    @NiceWeatherAppScope
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
