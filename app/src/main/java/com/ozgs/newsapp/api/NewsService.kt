package com.ozgs.newsapp.api

import androidx.lifecycle.LiveData
import com.ozgs.newsapp.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/top-headlines")
    fun getTopHeadlines(): LiveData<ApiResponse<List<News>>>
//    fun getTopHeadlines(@Query("country") country :String = "us"): LiveData<ApiResponse<List<News>>
}