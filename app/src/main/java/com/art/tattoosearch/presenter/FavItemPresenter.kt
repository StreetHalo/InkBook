package com.art.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.ui.favorite.item.ImgFavInterface

@InjectViewState

class FavItemPresenter(private val folderManager: FolderManager) : MvpPresenter<ImgFavInterface>(),ImgInterface {

    private var scrollPosition = 0


    init {
        folderManager.setPresenterInterface(this)
    }



    override fun saveImg() {
    }

    override fun delImg() {

        folderManager.delSavedPic(scrollPosition)

    }

    override fun setPosition(position: Int) {
            this.scrollPosition = position
    }

    override fun getPosition(): Int {
        return scrollPosition
    }

}