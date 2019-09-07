package com.art.tattoosearch.dagger

import com.art.tattoosearch.URL
import com.art.tattoosearch.views.search.gallery.GalleryAdapter
import com.art.tattoosearch.models.ConnectModel
import com.art.tattoosearch.presenter.SearchPresenter
import com.art.tattoosearch.views.favorite.gallery.FavAdapter
import com.art.tattoosearch.views.favorite.item.ImgFavAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class SearchModule() {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideConnectModel(retrofit: Retrofit): ConnectModel {
        return ConnectModel(retrofit)
    }

    @Provides
    fun     provideGalleryAdapter(): GalleryAdapter {
        return GalleryAdapter()
    }

    @Provides
    fun     provideFavGalleryAdapter(): FavAdapter {
        return FavAdapter()
    }

    @Provides
    fun     provideImgFavGalleryAdapter(): ImgFavAdapter {
        return ImgFavAdapter()
    }

    @Provides
    fun     provideSearchPresenter(connectModel: ConnectModel): SearchPresenter {
        return SearchPresenter(connectModel)
    }
    }