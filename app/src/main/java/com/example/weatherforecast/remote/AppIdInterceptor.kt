package com.example.weatherforecast.remote

import okhttp3.Interceptor
import okhttp3.Response

class AppIdInterceptor: Interceptor {
    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("appid", apiKey())
            .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()
        return chain.proceed(newRequest)
    }
}