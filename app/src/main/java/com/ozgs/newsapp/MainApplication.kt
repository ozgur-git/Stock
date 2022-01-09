package com.ozgs.newsapp

import android.app.Activity
import android.app.Application
import com.ozgs.newsapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication:Application(),HasActivityInjector  {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

//    @Inject
//    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        // initialize Dagger

        AppInjector.init(this)
//        DaggerAppComponent.builder().application(this).build().inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}