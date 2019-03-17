package com.example.tattoosearch.presenter

import com.example.tattoosearch.jsonModel.Item

interface SearchInterface {

    fun setConnect(query: String)

    fun setItems(items:ArrayList<Item>)

    fun emptySearch()

}