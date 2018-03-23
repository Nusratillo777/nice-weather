package com.monster.app.niceweather.application

import android.content.Context

import com.monster.app.niceweather.BuildConfig

import java.io.File

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by monster on 2/24/18.
 */

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @NiceWeatherAppScope
    fun cacheFile(context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()
        return cacheFile
    }

    @Provides
    @NiceWeatherAppScope
    fun cache(cacheFile: File) = Cache(cacheFile, (30 * 1024 * 1024).toLong()) // 30mb for cache

    @Provides
    @NiceWeatherAppScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @NiceWeatherAppScope
    fun okHttpClient(interceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()

                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("appid", BuildConfig.API_KEY)
                            .build()

                    val requestBuilder = original.newBuilder()
                            .url(url)

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                            .build()
                    val requestBuilder = original.newBuilder()
                            .url(url)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .cache(cache)
                .build()
    }
}
