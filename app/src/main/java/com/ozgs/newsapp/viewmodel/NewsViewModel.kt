package com.ozgs.newsapp.viewmodel

import androidx.lifecycle.*
import com.ozgs.newsapp.data.News
import com.ozgs.newsapp.data.NewsRepository
import com.ozgs.newsapp.vo.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor (
    private val newsRepository: NewsRepository
):ViewModel(){

    lateinit var newsList:LiveData<List<News>>
    val fetchedNews: MediatorLiveData<Resource<News>> = MediatorLiveData()

    init {

        viewModelScope.launch {
            newsList=Transformations.map(newsRepository.getNews()){
                it
            }
        }
    }

    fun fetchNews() {
//        fetchedNews.addSource(newsRepository.getMeteoData(longitude,latitude)){value->temp.value=value}
    }


//    fun getNews():LiveData<List<Articles>> = newsRepository.getNews()
//    fun getNews():LiveData<List<News>> = newsRepository.getNews()

}
