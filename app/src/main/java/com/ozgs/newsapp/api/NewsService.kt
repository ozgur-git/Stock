package com.ozgs.newsapp.api

import androidx.lifecycle.LiveData
import com.ozgs.newsapp.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("country") country :String = "us"): LiveData<ApiResponse<News>>
}