package com.example.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.ui.favorite.item.ImgFavInterface
import javax.inject.Inject

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