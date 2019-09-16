package com.art.tattoosearch.views.search.gallery

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.SEARCH_WORD
import com.art.tattoosearch.entities.Item
import com.art.tattoosearch.interactors.ImgInteractor
import com.art.tattoosearch.entities.ImageForView
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class SearchPresenter(private val imgInteractor: ImgInteractor) :
    MvpPresenter<SearchViewInterface>() {

    private var listItem: ArrayList<Item> = ArrayList()
    private var scrollPosition = 0
    private var num = 1
    private var query = "null"
    private val compositeDisposable = CompositeDisposable()
    private var isSync = false

    fun setConnect(query: String) {

        if (num < 99 && !isSync) {
            isSync = true
            val a = imgInteractor.getImgsFromGoogle(query, num)
                .cache()
                .subscribe(
                    { it ->
                        setItems(ArrayList(it.items))
                        isSync = false
                    },
                    { e -> viewState.errorSearch() }
                )
            compositeDisposable.add(a)
            num += 11
        }

    }

    fun setItems(items: ArrayList<Item>) {
        listItem.addAll(items)
        viewState.setItems(listItem)
    }

    fun setScrollPosition(scrollPosition: Int) {
        this.scrollPosition = scrollPosition
        if (scrollPosition + 2 > listItem.size) setConnect(query)
    }

    fun getScrollPosition(): Int {
        return scrollPosition
    }

    fun getImgList(): ArrayList<ImageForView> {

        val imgList = ArrayList<ImageForView>()
        for (item in listItem) {
            val img = ImageForView(
                item.link!!,
                0,
                item.image!!.thumbnailLink!!
            )
            imgList.add(img)
        }
        return imgList
    }

    fun setQuery(query: String) {
        if (query != "") {
            this.query = SEARCH_WORD + query
            num = 1
            listItem.clear()
            setConnect(this.query)
        }
    }

    fun setSyncIsFalse() {
        isSync = false
    }


    fun detach() {
        compositeDisposable.dispose()
    }
}


