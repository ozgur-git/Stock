package com.ozgs.newsapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NewsTable")
data class News(
    @PrimaryKey
    val ord:Int=0,
    val status:String="",
    val totalResults:Int=0,
    val articles:List<NewsItem>
    ){
    data class NewsItem(
        @Embedded
        val source:Agency,
        val author: String?="",
        val title:String="",
        val description:String="",
        val url:String="",
        val urlToImage:String="",
        val publishedAt:String="",
        val content:String=""
    ){
        data class Agency(
            val id:String="",
            val name:String=""
        )
    }
}
