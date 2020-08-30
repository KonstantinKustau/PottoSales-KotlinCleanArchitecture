package com.thebestdiscountandroid

import android.app.Application
import com.thebestdiscountandroid.core.di.ApplicationComponent
import com.thebestdiscountandroid.core.di.ApplicationModule
import com.thebestdiscountandroid.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
//        TODO leak
//        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

//    TODO leak
//    private fun initializeLeakDetection() {
//        if (BuildConfig.DEBUG) LeakCanary.install(this)
//    }
}