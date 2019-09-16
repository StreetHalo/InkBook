package com.art.tattoosearch.views.favorite.item

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.interactors.ImgInteractor
import com.art.tattoosearch.repository.FolderManager
import io.reactivex.disposables.CompositeDisposable

@InjectViewState

class FavItemPresenter(private val imgInteractor: ImgInteractor) : MvpPresenter<ImgFavInterface>() {

    private var scrollPosition = 0
    private var imgList = ArrayList<String>()
    private val compositeDisposable = CompositeDisposable()

    fun delImg() {

        val a = imgInteractor.delImgFromStorage(imgList[scrollPosition])?.subscribe(
            { isDel: Boolean? ->
                if (isDel!!) viewState.updateAdapter(scrollPosition)
                else viewState.fileIsNotDel()
            },
            { e -> viewState.delIsError() }
        )

        compositeDisposable.add(a!!)
    }

    fun setPosition(position: Int) {
        this.scrollPosition = position
    }

    fun getPosition(): Int {
        return scrollPosition
    }

    fun setImgList(listImg: ArrayList<String>?) {
        imgList = listImg!!
    }
}