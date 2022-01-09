package com.ozgs.newsapp.di

import com.ozgs.newsapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class]) // Where to apply the injection.
    abstract fun contributeMainActivity(): MainActivity

}