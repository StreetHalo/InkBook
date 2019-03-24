package com.art.tattoosearch.ui.favorite.gallery

import com.arellomobile.mvp.MvpView

interface FavViewInterface:MvpView {
    fun setItems(items:ArrayList<String>)

    fun setClickPosition(clickPosition:Int)

    fun emptyFolder()

}