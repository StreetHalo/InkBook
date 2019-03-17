package com.example.tattoosearch

import android.app.Application
import com.example.tattoosearch.dagger.DaggerMainComponent
import com.example.tattoosearch.dagger.MainComponent

class App: Application() {

    companion object {
        lateinit var daggerMainComponent: MainComponent
    }
    override fun onCreate() {
        super.onCreate()
         daggerMainComponent = DaggerMainComponent.builder().build()
    }
}