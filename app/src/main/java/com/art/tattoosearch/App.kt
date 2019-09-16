package com.art.tattoosearch

import android.app.Application
import com.art.tattoosearch.DI.DaggerMainComponent
import com.art.tattoosearch.DI.ImgModule
import com.art.tattoosearch.DI.MainComponent
import com.art.tattoosearch.DI.MainModule

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