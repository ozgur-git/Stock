package com.ozgs.newsapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.ozgs.newsapp.api.ApiResponse
import com.ozgs.newsapp.api.NewsService
import com.ozgs.newsapp.utilities.NetworkBoundResource
import com.ozgs.newsapp.vo.Resource
import com.ozgs.newsapp.workers.AppExecutors
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val appExecutors: AppExecutors,
    private val tempService: NewsService
) {
    fun getNews(): LiveData<News> = newsDao.getNews()

    fun fetchNews(shouldFetch: Boolean = true): LiveData<Resource<News>> {
        return object : NetworkBoundResource<News, News>(appExecutors) {
            override fun saveCallResult(item: News) {
                    newsDao.insert(item)
                }

            override fun shouldFetch(data: News?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<News> {
                return newsDao.getNews()
            }

            override fun createCall(): LiveData<ApiResponse<News>> {
                Log.d("ntwrk", "fetch is called ")
                return tempService.getTopHeadlines()
            }

        }.asLiveData()
    }
}
