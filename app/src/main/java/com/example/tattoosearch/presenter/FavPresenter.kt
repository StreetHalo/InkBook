package com.example.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.ui.favorite.gallery.FavViewInterface
import javax.inject.Inject

@InjectViewState
class FavPresenter: MvpPresenter<FavViewInterface>() {

    @Inject
    lateinit var folderManager: FolderManager
    private var scrollPosition = 0


    init {
        App.daggerMainComponent.inject(this)
    }

    private fun setListFavImges(savedPics: ArrayList<String>) {
        if(savedPics.size==0) viewState.emptyFolder()
        viewState.setItems(savedPics)

    }
    fun setScrollPosition(scrollPosition:Int){
        this.scrollPosition = scrollPosition
    }


    fun getScrollPosition(): Int{
        return scrollPosition
    }


    fun updateAdapter(){
        setListFavImges(folderManager.getSavedPics())
    }

    fun getSavedList():ArrayList<String>{
        return folderManager.getSavedPics()
    }
}