package com.example.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.ui.favorite.item.ImgFavInterface
import com.example.tattoosearch.ui.search.image.ImageViewInterface
import javax.inject.Inject

@InjectViewState

class FavItemPresenter: MvpPresenter<ImgFavInterface>(),ImgInterface {

    @Inject
    lateinit var folderManager: FolderManager
    private var scrollPosition = 0


    init {
        App.daggerMainComponent.inject(this)
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