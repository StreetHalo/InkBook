package com.art.tattoosearch

import android.app.Application
import com.art.tattoosearch.di.DaggerMainComponent
import com.art.tattoosearch.di.ImgModule
import com.art.tattoosearch.di.MainComponent
import com.art.tattoosearch.di.MainModule

class App : Application() {

    companion object {
        lateinit var daggerMainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerMainComponent = DaggerMainComponent.builder()
            .mainModule(MainModule(applicationContext))
            .imgModule(ImgModule(applicationContext))
            .build()
    }
}