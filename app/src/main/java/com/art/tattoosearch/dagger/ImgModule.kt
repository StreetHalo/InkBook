package com.art.tattoosearch.dagger

import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.presenter.FavItemPresenter
import com.art.tattoosearch.presenter.FavPresenter
import com.art.tattoosearch.presenter.ImgPresenter
import com.art.tattoosearch.ui.search.image.ImgAdapter
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