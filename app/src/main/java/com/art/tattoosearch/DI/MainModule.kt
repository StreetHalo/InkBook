package com.art.tattoosearch.DI

import android.content.Context
import com.art.tattoosearch.views.favorite.gallery.FavAdapter
import com.art.tattoosearch.views.favorite.item.ImgFavAdapter
import com.art.tattoosearch.views.main.MainActivityPresenter
import com.art.tattoosearch.views.search.gallery.GalleryAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule(val context: Context) {

    @Provides
    fun provideMainPresenter(): MainActivityPresenter {
        return MainActivityPresenter(context)
    }

    @Provides
    fun provideGalleryAdapter(): GalleryAdapter {
        return GalleryAdapter()
    }

    @Provides
    fun provideFavGalleryAdapter(): FavAdapter {
        return FavAdapter()
    }

    @Provides
    fun provideImgFavGalleryAdapter(): ImgFavAdapter {
        return ImgFavAdapter()
    }
}