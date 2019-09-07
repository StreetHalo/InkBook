package com.art.tattoosearch.dagger

import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.views.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideFolderManager(): FolderManager {
        return FolderManager()
    }
    @Provides
    fun provideSplashPresenter(folderManager: FolderManager):SplashPresenter{
        return SplashPresenter(folderManager)
    }
}