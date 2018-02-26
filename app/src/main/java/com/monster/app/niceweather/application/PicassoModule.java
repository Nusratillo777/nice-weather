package com.monster.app.niceweather.application;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by monster on 2/24/18.
 */
@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @NiceWeatherAppScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

    @Provides
    @NiceWeatherAppScope
    public Picasso picasso(Context context, OkHttp3Downloader downloader) {
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }
}
