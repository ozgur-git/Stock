package com.ozgs.newsapp.api

import androidx.lifecycle.LiveData
import com.ozgs.newsapp.data.News
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers(
        "X-Api-Key: 9b396ea8cc8c4c7a934f0a6af79d8fcb"
    )
    @GET("v2/top-headlines")
    fun getTopHeadlines(): LiveData<ApiResponse<News>>
//    fun getTopHeadlines(@Query("country") country :String = "us"): LiveData<ApiResponse<List<News>>
}