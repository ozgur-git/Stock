package com.ozgs.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.ozgs.newsapp.data.NewsRepository
import javax.inject.Inject

class NewsViewModel @Inject constructor (
    private val newsRepository: NewsRepository
):ViewModel(){

    init {
        newsRepository.getNews()
    }

}
