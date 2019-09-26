package com.art.tattoosearch.di

import com.art.tattoosearch.URL
import com.art.tattoosearch.interactors.ImgInteractor
import com.art.tattoosearch.repository.FolderManager
import com.art.tattoosearch.views.search.gallery.SearchPresenter
import com.art.tattoosearch.repository.ImgFromGoogleRepository
import com.art.tattoosearch.views.favorite.gallery.FavPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class GalleryModule() {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideImgFromGoogleRepository(retrofit: Retrofit): ImgFromGoogleRepository {
        return ImgFromGoogleRepository(retrofit)
    }

    @Provides
    fun provideImgInteractor(
        imgRepository: ImgFromGoogleRepository,
        folderManager: FolderManager
    ): ImgInteractor {
        return ImgInteractor(imgRepository, folderManager)
    }

    @Provides
    fun provideSearchPresenter(imgInteractor: ImgInteractor): SearchPresenter {
        return SearchPresenter(imgInteractor)
    }

    @Provides
    fun provideFavPresenter(imgInteractor: ImgInteractor): FavPresenter {
        return FavPresenter(imgInteractor)
    }
}