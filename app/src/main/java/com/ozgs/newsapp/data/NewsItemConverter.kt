package com.ozgs.newsapp.data

import androidx.annotation.Keep
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Keep
class NewsItemConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(input: String): List<NewsItem> {
        val listType = object : TypeToken<List<NewsItem>>() {}.type
        return gson.fromJson(input, listType)
    }

    @TypeConverter
    fun listToJSON(list: List<NewsItem>): String {
        return gson.toJson(list)
    }
}

