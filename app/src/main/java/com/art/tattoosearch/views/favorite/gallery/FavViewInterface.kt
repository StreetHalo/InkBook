package com.art.tattoosearch.views.favorite.gallery

import com.arellomobile.mvp.MvpView

interface FavViewInterface:MvpView {
    fun setItems(items:ArrayList<String>)

    fun setClickPosition(clickPosition:Int)

    fun emptyFolder()

}