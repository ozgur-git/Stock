package com.ozgs.newsapp.data

import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Keep
class NewsItemConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(input: String): List<News.NewsItem> {
        val listType = object : TypeToken<List<News.NewsItem>>() {}.type
        return gson.fromJson(input, listType)
    }

    @TypeConverter
    fun listToJSON(list: List<News.NewsItem>): String {
        return gson.toJson(list)
    }
}

