package com.art.tattoosearch.repository

import API_KEY
import SEARCH_ID
import com.art.tattoosearch.IMAGE_SIZE
import com.art.tattoosearch.SEARCH_TYPE
import com.art.tattoosearch.entities.Example
import com.art.tattoosearch.models.SearchService
import io.reactivex.Observable
import retrofit2.Retrofit


open class ImgFromGoogleRepository(val retrofit: Retrofit) {
    private val placesApi = retrofit.create(SearchService::class.java)


    fun getImages(query:String, start:Int): Observable<Example> {
        return placesApi.getImg(query, SEARCH_ID,start,1, IMAGE_SIZE, SEARCH_TYPE, API_KEY)
    }
}