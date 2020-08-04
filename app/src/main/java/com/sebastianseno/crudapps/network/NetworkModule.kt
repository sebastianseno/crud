package com.sebastianseno.crudapps.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sebastianseno.crudapps.BuildConfig
import com.sebastianseno.crudapps.network.service.GoodsServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesResponseInterceptor(): ResponseInterceptor {
        return ResponseInterceptor()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        responseInterceptor: ResponseInterceptor,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().also {
                responseInterceptor.retrofit = it
            }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(responseInterceptor)
        builder.addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGoodsServices(retrofit: Retrofit): GoodsServices {
        return retrofit.create(GoodsServices::class.java)
    }
}