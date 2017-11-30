package com.reza.commbank.commBank.module

import android.content.Context
import com.reza.commbank.commBank.util.API
import com.reza.commbank.commBank.util.ApiInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by reza on 30/11/17.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideApiInterceptor(): ApiInterceptor {
        return ApiInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: okhttp3.Cache, apiInterceptor: ApiInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiInterceptor)
                .connectTimeout(API.TIMEOUT_IN_MS.value.toLong(), TimeUnit.MILLISECONDS)
                .cache(cache).build()
    }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {

        val cacheSize: Long = 5 * 1024 * 1024
        var cacheDir: File = context.cacheDir
        return Cache(cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {

        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, rxAdaptor: RxJava2CallAdapterFactory) : Retrofit {


        return Retrofit.Builder()
                .baseUrl(API.BASE_URL.value)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdaptor)
                .client(okHttpClient)
                .build()
    }
}