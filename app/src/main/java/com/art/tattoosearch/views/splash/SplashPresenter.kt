package com.art.tattoosearch.views.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.models.FolderManager

@InjectViewState
open class SplashPresenter(val folderManager: FolderManager): MvpPresenter<SplashInterface>() {

    fun createFolder(path:String){
        if(folderManager.createFolder(path))
            viewState.openMainActivity()
        else viewState.error()

    }
}