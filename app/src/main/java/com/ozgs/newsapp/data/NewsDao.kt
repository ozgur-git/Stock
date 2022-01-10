package com.ozgs.newsapp.data

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.room.*

@Keep
@Dao
interface NewsDao {

    @Query("select articles from NewsTable")
    fun getNewsItems(): LiveData<List<NewsItem>>

    @Query("select * from NewsTable")
    fun getNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsItem: NewsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tempData:List<News>)

}