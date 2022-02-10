package com.example.weatherforecast.remote

import com.example.weatherforecast.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AppIdInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()
        return chain.proceed(newRequest)
    }
}