package com.ozgs.newsapp.data

import androidx.lifecycle.LiveData
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
    fun getNews(): LiveData<List<News>> = newsDao.getNews()

//    fun fetchNews(shouldFetch: Boolean = true):LiveData<Resource<List<News>>> {
//        return object : NetworkBoundResource<List<News>, News>(appExecutors) {
//            override fun saveCallResult(item: News) {
//                newsDao.deleteNearby()
//                item.geonames.forEach {
//                    it.isNearby = true
//                    newsDao.insertFavorite(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<News>?): Boolean {
//                return shouldFetch
//            }
//
//            override fun loadFromDb(): LiveData<List<News>> {
//                return newsDao.getNearby()
//            }
//
//            override fun createCall() = tempService.getTopHeadlines()
//
//        }.asLiveData()
//    }
}
