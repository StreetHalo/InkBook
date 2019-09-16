package com.art.tattoosearch.views.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.repository.FolderManager

@InjectViewState
open class SplashPresenter(val folderManager: FolderManager) : MvpPresenter<SplashInterface>() {

    fun createFolder(path: String) {
        if (folderManager.createFolder(path))
            viewState.openMainActivity()
        else viewState.error()
    }

    fun checkPermissions(granted: Boolean) {
        if (granted) {
            createFolder(PATH_FOR_IMG)
        } else {
            viewState.needPermissions()
        }
    }
}