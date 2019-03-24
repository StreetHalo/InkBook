package com.art.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.SEARCH_WORD
import com.art.tattoosearch.jsonModel.Item
import com.art.tattoosearch.models.ConnectModel
import com.art.tattoosearch.models.Image
import com.art.tattoosearch.ui.search.gallery.SearchViewInterface


@InjectViewState
class SearchPresenter(private val connectModel: ConnectModel) : MvpPresenter<SearchViewInterface>(),SearchInterface {

    private var listItem:ArrayList<Item> = ArrayList()
    private var scrollPosition = 0
    private var num = 1
    private var query = "null"

     init {
            connectModel.setPresenterInterface(this)
     }



override fun setConnect(query: String) {
        if(num<99) {
            connectModel.getArrayImg(query, num)
            num += 11
        }

    }

 override fun setItems(items: ArrayList<Item>) {
         listItem.addAll(items)
     viewState.setItems(listItem)
    }

    fun setScrollPosition(scrollPosition:Int){
        this.scrollPosition = scrollPosition
        if(scrollPosition+2>listItem.size) setConnect(query)
    }

    fun getScrollPosition(): Int{
        return scrollPosition
    }

    fun getImgList():ArrayList<Image>{

        val imgList = ArrayList<Image>()
        for(item in listItem){
            val img = Image(item.link!!,0, item.image!!.thumbnailLink!!)
            imgList.add(img)
        }

        return imgList
    }
    fun setQuery(query: String){
        this.query = SEARCH_WORD + query
                num = 1
        listItem.clear()
        setConnect(this.query)
    }

    override fun emptySearch(){
        viewState.errorSearch()
    }
}