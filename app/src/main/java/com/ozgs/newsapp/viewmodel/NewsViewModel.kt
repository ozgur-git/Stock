package com.ozgs.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgs.newsapp.data.Articles
import com.ozgs.newsapp.data.News
import com.ozgs.newsapp.data.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor (
    private val newsRepository: NewsRepository
):ViewModel(){

    lateinit var newsList:LiveData<List<News>>

    init {

        viewModelScope.launch {
            newsList=Transformations.map(newsRepository.getNews()){
                it
            }
        }
    }

//    fun getNews():LiveData<List<Articles>> = newsRepository.getNews()
//    fun getNews():LiveData<List<News>> = newsRepository.getNews()

}
