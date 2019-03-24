package com.art.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.ui.favorite.gallery.FavViewInterface

@InjectViewState
class FavPresenter(private val folderManager: FolderManager) : MvpPresenter<FavViewInterface>() {

    private var scrollPosition = 0


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