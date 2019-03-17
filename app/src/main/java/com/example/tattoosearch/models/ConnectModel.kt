package com.example.tattoosearch.models

import com.example.tattoosearch.API_KEY
import com.example.tattoosearch.IMAGE_SIZE
import com.example.tattoosearch.presenter.SearchInterface
import com.example.tattoosearch.SEARCH_ID
import com.example.tattoosearch.SEARCH_TYPE
import com.example.tattoosearch.jsonModel.Example
import com.example.tattoosearch.jsonModel.Item
import retrofit2.*
import java.lang.Exception

class ConnectModel  constructor( retrofit: Retrofit) {

    private lateinit var presenterInterface:SearchInterface

    private val apiServiceImages = retrofit.create(SearchService::class.java)

    fun setPresenterInterface( presenterInterface: SearchInterface){
        this.presenterInterface = presenterInterface
    }

    fun getArrayImg(query:String, start:Int){

        apiServiceImages.getImg(query, SEARCH_ID,start,1,IMAGE_SIZE, SEARCH_TYPE, API_KEY)
            .enqueue(object : Callback<Example>{
                override fun onFailure(call: Call<Example>, t: Throwable) {
                }

                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    try {
                        presenterInterface.setItems(response.body()?.items as ArrayList<Item>)

                    } catch (e: Exception) {
                        presenterInterface.emptySearch()
                    }
                }
            })
    }
}