package com.example.tattoosearch.ui.search.gallery

import com.arellomobile.mvp.MvpView
import com.example.tattoosearch.jsonModel.Item

interface SearchViewInterface:MvpView {

    fun setItems(items:ArrayList<Item>)

    fun errorSearch()

    fun setClickPosition(clickPosition:Int)
}