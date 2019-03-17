package com.example.tattoosearch.ui.favorite.gallery

import android.support.design.circularreveal.CircularRevealHelper
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface FavViewInterface:MvpView {
    fun setItems(items:ArrayList<String>)

    fun setClickPosition(clickPosition:Int)

    fun emptyFolder()

}