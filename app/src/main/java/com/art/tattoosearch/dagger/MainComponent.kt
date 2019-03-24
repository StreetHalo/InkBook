package com.art.tattoosearch.dagger

import com.art.tattoosearch.ui.MainActivity
import com.art.tattoosearch.ui.favorite.gallery.FavFragment
import com.art.tattoosearch.ui.favorite.item.ImageFavActivity
import com.art.tattoosearch.ui.search.image.ImageActivity
import com.art.tattoosearch.ui.search.gallery.GalleryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(SearchModule::class,ImgModule::class))

interface MainComponent {

    fun inject(app: MainActivity)

    fun inject(fragment: GalleryFragment)

    fun inject(app: ImageActivity)

    fun inject(app: FavFragment)

    fun inject(app: ImageFavActivity)

}