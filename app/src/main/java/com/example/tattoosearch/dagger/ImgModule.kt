package com.example.tattoosearch.dagger

import com.example.tattoosearch.PATH_FOR_IMG
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.presenter.FavItemPresenter
import com.example.tattoosearch.presenter.FavPresenter
import com.example.tattoosearch.presenter.ImgPresenter
import com.example.tattoosearch.ui.search.image.ImgAdapter
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
open class ImgModule{


    @Provides
    fun     provideImgAdapter(): ImgAdapter {
        return ImgAdapter()
    }
    @Singleton
    @Provides
    fun provideFolder(): File{
        return File(PATH_FOR_IMG)
    }
    @Singleton
    @Provides
    fun provideFolderManager(file: File):FolderManager{
        return FolderManager(file)
    }

    @Provides
    fun provideFavItemPresenter(folderManager: FolderManager):FavItemPresenter{
        return FavItemPresenter(folderManager)
    }

    @Provides
    fun provideItemPresenter(folderManager: FolderManager):ImgPresenter{
        return ImgPresenter(folderManager)
    }

    @Provides
    fun provideFavPresenter(folderManager: FolderManager):FavPresenter{
        return FavPresenter(folderManager)
    }
}