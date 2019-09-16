package com.art.tattoosearch.views.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.art.tattoosearch.FAVORITE_ITEM

class MainActivityPresenter(val context: Context) {

    lateinit var viewInterface: MainActivityInterface

    fun onAttach(viewInterface: MainActivityInterface) {
        this.viewInterface = viewInterface
    }

    fun setQuery(query: String?) {
        if (query != null) {
            if (isConnected()) {
                viewInterface.setSearchFragment(query)
            }
        } else viewInterface.needConnect()
    }

    private fun isConnected(): Boolean {
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun itemSelected(item: String?) {
        when (item) {

            FAVORITE_ITEM -> {
                viewInterface.setFavFragment()
            }
        }
    }
}