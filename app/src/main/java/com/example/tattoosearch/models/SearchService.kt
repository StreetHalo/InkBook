package com.example.tattoosearch.models

import com.example.tattoosearch.jsonModel.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/customsearch/v1?")
    fun getImg(@Query("q") query: String,
               @Query("cx") searchID: String,
               @Query("start") start: Int,
               @Query("filter") filter: Int,
               @Query("imgSize") size: String,
               @Query("searchType")searchType:String,
               @Query ("key")key:String): Call<Example>
}