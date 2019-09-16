package com.art.tattoosearch.views.favorite.gallery

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.interactors.ImgInteractor
import com.art.tattoosearch.repository.FolderManager

@InjectViewState
class FavPresenter(private val imgInteractor: ImgInteractor) : MvpPresenter<FavViewInterface>() {

    private var scrollPosition = 0


    private fun setListFavImges(savedPics: ArrayList<String>) {
        if (savedPics.size == 0) viewState.emptyFolder()
        viewState.setItems(savedPics)

    }

    fun setScrollPosition(scrollPosition: Int) {
        this.scrollPosition = scrollPosition
    }


    fun getScrollPosition(): Int {
        return scrollPosition
    }


    fun updateAdapter() {
        setListFavImges(imgInteractor.getImgsFromStorage())
    }

    fun getSavedList(): ArrayList<String> {
        return imgInteractor.getImgsFromStorage()
    }
}