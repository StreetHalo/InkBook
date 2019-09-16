package com.art.tattoosearch.DI

import android.content.Context
import com.art.tattoosearch.interactors.ImgInteractor
import com.art.tattoosearch.views.favorite.item.FavItemPresenter
import com.art.tattoosearch.views.search.image.ImgPresenter
import com.art.tattoosearch.views.search.image.ImgAdapter
import dagger.Module
import dagger.Provides

@Module
open class ImgModule(val context: Context) {

    @Provides
    fun provideImgAdapter(): ImgAdapter {
        return ImgAdapter()
    }

    @Provides
    fun provideFavItemPresenter(imgInteractor: ImgInteractor): FavItemPresenter {
        return FavItemPresenter(imgInteractor)
    }

    @Provides
    fun provideItemPresenter(): ImgPresenter {
        return ImgPresenter(context)
    }
}