package com.example.tattoosearch.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.SEARCH_WORD
import com.example.tattoosearch.jsonModel.Item
import com.example.tattoosearch.models.ConnectModel
import com.example.tattoosearch.models.Image
import com.example.tattoosearch.ui.search.gallery.SearchViewInterface
import javax.inject.Inject


@InjectViewState
class SearchPresenter : MvpPresenter<SearchViewInterface>(),SearchInterface {


    @Inject
    lateinit var connectModel: ConnectModel
    private var listItem:ArrayList<Item> = ArrayList()
    private var scrollPosition = 0
    private var num = 1
    private var query = "null"
     init {
            App.daggerMainComponent.inject(this)
            connectModel.setPresenterInterface(this)
     }



override fun setConnect(query: String) {
        if(num<20) {
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