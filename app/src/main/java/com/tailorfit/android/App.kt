package com.tailorfit.android

import android.app.Application
import com.tailorfit.android.di.AppComponent
import com.tailorfit.android.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
