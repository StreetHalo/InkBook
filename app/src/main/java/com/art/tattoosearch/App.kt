package com.art.tattoosearch

import android.app.Application
import com.art.tattoosearch.dagger.DaggerMainComponent
import com.art.tattoosearch.dagger.MainComponent

class App: Application() {

    companion object {
        lateinit var daggerMainComponent: MainComponent
    }
    override fun onCreate() {
        super.onCreate()
         daggerMainComponent = DaggerMainComponent.builder().build()
    }
}