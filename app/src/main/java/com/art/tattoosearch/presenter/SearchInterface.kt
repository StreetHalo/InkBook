package com.art.tattoosearch.presenter

import com.art.tattoosearch.jsonModel.Item

interface SearchInterface {

    fun setConnect(query: String)

    fun setItems(items:ArrayList<Item>)

    fun emptySearch()

}