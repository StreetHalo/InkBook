package com.example.tattoosearch.dagger

import com.example.tattoosearch.presenter.FavItemPresenter
import com.example.tattoosearch.presenter.FavPresenter
import com.example.tattoosearch.presenter.ImgPresenter
import com.example.tattoosearch.ui.MainActivity
import com.example.tattoosearch.presenter.SearchPresenter
import com.example.tattoosearch.ui.favorite.gallery.FavFragment
import com.example.tattoosearch.ui.favorite.item.ImageFavActivity
import com.example.tattoosearch.ui.search.image.ImageActivity
import com.example.tattoosearch.ui.search.gallery.GalleryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(SearchModule::class,ImgModule::class))

interface MainComponent {

    fun inject(app: MainActivity)

    fun inject(fragment: GalleryFragment)

    fun inject(presenter: SearchPresenter)

    fun inject(presenter: ImgPresenter)

    fun inject(app: ImageActivity)

    fun inject(app: FavFragment)

    fun inject(app: FavPresenter)

    fun inject(app: ImageFavActivity)

    fun inject(app: FavItemPresenter)

}