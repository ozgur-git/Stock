package com.ozgs.newsapp.api

import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest=chain.request()
        val newUrl=chain.request().url().newBuilder()
//            .addQueryParameter("username", Level.getTwo())
            .addQueryParameter("country","us")
            .build()
        val newRequest=originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}