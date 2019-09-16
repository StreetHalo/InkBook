package com.art.tattoosearch.interactors

import com.art.tattoosearch.entities.Example
import com.art.tattoosearch.repository.FolderManager
import com.art.tattoosearch.repository.ImgFromGoogleRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class ImgInteractor(
    private val imgGoogleRepository: ImgFromGoogleRepository,
    private val folderManager: FolderManager
) {

    fun getImgsFromGoogle(query: String, start: Int): Observable<Example> {
        return imgGoogleRepository.getImages(query, start)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getImgsFromStorage(): ArrayList<String> {
        return folderManager.getSavedPics()
    }

    fun delImgFromStorage(name: String): Observable<Boolean>? {
        return Observable.fromCallable {
            return@fromCallable folderManager.delSavedPic(name)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}