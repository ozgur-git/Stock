package com.ozgs.newsapp.data

import androidx.lifecycle.LiveData
import com.ozgs.newsapp.api.NewsService
import com.ozgs.newsapp.workers.AppExecutors
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val tempDao: NewsDao,
    private val appExecutors: AppExecutors,
    private val mTempService: NewsService
) {

    fun getNews(): LiveData<List<News>> = tempDao.getNews()


}