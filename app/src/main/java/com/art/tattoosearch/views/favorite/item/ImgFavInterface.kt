package com.art.tattoosearch.views.favorite.item

import com.arellomobile.mvp.MvpView

interface ImgFavInterface : MvpView {
    fun updateAdapter(scrollPosition: Int)

    fun fileIsNotDel()

    fun delIsError()

}