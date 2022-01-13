package com.ozgs.newsapp.data

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.room.*

@Keep
@Dao
interface NewsDao {


    @Query("select * from NewsTable")
    fun getNews(): LiveData<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tempData:News)

}