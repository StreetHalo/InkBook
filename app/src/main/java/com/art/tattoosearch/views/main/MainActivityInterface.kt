package com.art.tattoosearch.views.main

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainActivityInterface {
    fun needConnect()
    fun setSearchFragment(query: String)
    fun setFavFragment()
}