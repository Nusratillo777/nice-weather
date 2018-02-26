package com.monster.app.niceweather.application;

import android.content.Context;

import com.monster.app.niceweather.BuildConfig;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by monster on 2/24/18.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @NiceWeatherAppScope
    public File cacheFile(Context context) {
        File cacheFile = new File(context.getCacheDir(), "okhttp_cache");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    @NiceWeatherAppScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 30 * 1024 * 1024); // 30mb for cache
    }

    @Provides
    @NiceWeatherAppScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @NiceWeatherAppScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()

                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("appid", BuildConfig.API_KEY)
                            .build();

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .build();
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }
}
