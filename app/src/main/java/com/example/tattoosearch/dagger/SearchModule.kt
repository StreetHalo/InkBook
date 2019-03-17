package com.example.tattoosearch.dagger

import com.example.tattoosearch.URL
import com.example.tattoosearch.ui.search.gallery.GalleryAdapter
import com.example.tattoosearch.models.ConnectModel
import com.example.tattoosearch.ui.favorite.gallery.FavAdapter
import com.example.tattoosearch.ui.favorite.item.ImgFavAdapter
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
    }