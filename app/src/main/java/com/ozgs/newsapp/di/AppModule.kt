package com.ozgs.newsapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ozgs.newsapp.api.NewsInterceptor
import com.ozgs.newsapp.api.NewsService
import com.ozgs.newsapp.data.AppDatabase
import com.ozgs.newsapp.data.NewsDao
import com.ozgs.newsapp.utilities.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module (includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(provideNewsOkHttpClient())
            .build()
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(NewsInterceptor()).build()
    }

    @Singleton
    @Provides
    fun providerNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideSharedPrefs(app:Application):SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app.applicationContext)
    }

}