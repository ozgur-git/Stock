package com.ozgs.newsapp.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest=chain.request()
        val newUrl=chain.request().url().newBuilder()
//            .addQueryParameter("username", Level.getTwo())
            .addQueryParameter("country","us")
//            .addQueryParameter("apiKey","9b396ea8cc8c4c7a934f0a6af79d8fcb")
            .build()
        Log.d("urlis","url is $newUrl")
        val newRequest=originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}