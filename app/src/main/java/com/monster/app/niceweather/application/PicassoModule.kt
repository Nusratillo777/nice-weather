package com.monster.app.niceweather.application

import android.content.Context

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Created by monster on 2/24/18.
 */
@Module(includes = [ContextModule::class, NetworkModule::class])
class PicassoModule {

    @Provides
    @NiceWeatherAppScope
    fun okHttp3Downloader(okHttpClient: OkHttpClient) = OkHttp3Downloader(okHttpClient)

    @Provides
    @NiceWeatherAppScope
    fun picasso(context: Context, downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(downloader)
                .build()
    }
}
