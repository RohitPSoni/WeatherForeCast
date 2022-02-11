package com.example.weatherforecast.di

import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.remote.ApiMapper
import com.example.weatherforecast.remote.ApiMapperImpl
import com.example.weatherforecast.remote.AppIdInterceptor
import com.example.weatherforecast.remote.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit.Builder

val networkModule = module {
    single { ApiMapperImpl() } bind ApiMapper::class
    single { AppIdInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideExchangeApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(interceptor: AppIdInterceptor): OkHttpClient {
    val client = OkHttpClient().newBuilder()
    if (BuildConfig.DEBUG) {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        client.addInterceptor(logger)
    }
    client.addInterceptor(interceptor)
    return client.build()
}

fun provideExchangeApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Builder().baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}