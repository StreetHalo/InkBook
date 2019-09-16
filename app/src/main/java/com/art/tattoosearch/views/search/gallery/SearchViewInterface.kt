package com.art.tattoosearch.views.search.gallery

import android.support.design.circularreveal.CircularRevealHelper
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.art.tattoosearch.entities.Item
interface SearchViewInterface:MvpView {

    fun setItems(items:ArrayList<Item>)

    fun errorSearch()

    fun setClickPosition(clickPosition:Int)
}