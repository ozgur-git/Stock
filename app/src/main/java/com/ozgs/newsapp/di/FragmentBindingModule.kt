package com.ozgs.newsapp.di

import com.ozgs.newsapp.ui.NewsItemFragment
import com.ozgs.newsapp.ui.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    internal abstract fun mapsFragment(): NewsListFragment
    @ContributesAndroidInjector
    internal abstract fun searchFragment(): NewsItemFragment
}