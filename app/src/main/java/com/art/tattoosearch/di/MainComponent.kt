package com.art.tattoosearch.di

import com.art.tattoosearch.views.main.MainActivity
import com.art.tattoosearch.views.favorite.gallery.FavFragment
import com.art.tattoosearch.views.favorite.item.ImageFavActivity
import com.art.tattoosearch.views.search.image.ImageActivity
import com.art.tattoosearch.views.search.gallery.GalleryFragment
import com.art.tattoosearch.views.splash.SplashActivity
import dagger.Component

@Component(
    modules = arrayOf(
        GalleryModule::class,
        ImgModule::class,
        SplashModule::class,
        MainModule::class
    )
)

interface MainComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(app: MainActivity)

    fun inject(fragment: GalleryFragment)

    fun inject(app: ImageActivity)

    fun inject(app: FavFragment)

    fun inject(app: ImageFavActivity)

}