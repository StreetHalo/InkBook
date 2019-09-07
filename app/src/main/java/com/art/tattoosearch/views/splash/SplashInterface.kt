package com.art.tattoosearch.views.splash

import com.arellomobile.mvp.MvpView

interface SplashInterface:MvpView {

   fun openMainActivity()

    fun needPermissions()

    fun error()
}