package com.art.tattoosearch.dagger

import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.presenter.FavItemPresenter
import com.art.tattoosearch.presenter.FavPresenter
import com.art.tattoosearch.presenter.ImgPresenter
import com.art.tattoosearch.views.search.image.ImgAdapter
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