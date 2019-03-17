package com.example.tattoosearch.ui.search.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tattoosearch.R
import kotlinx.android.synthetic.main.gallery_fragment.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.HORIZONTAL_ORIENTATION
import com.example.tattoosearch.jsonModel.Item
import com.example.tattoosearch.presenter.SearchPresenter
import com.example.tattoosearch.ui.search.image.ImageActivity
import javax.inject.Inject


class GalleryFragment : MvpAppCompatFragment(), SearchViewInterface {

    private val TAG = this.javaClass.simpleName
    private var searchFlag = false
    lateinit var  gridLayoutManager:GridLayoutManager

    @Inject
    lateinit var adapter: GalleryAdapter
    @InjectPresenter
    lateinit var presenter: SearchPresenter
    var query = "null"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gallery_fragment,container,false)
    }

    override fun onAttach(context: Context?) {
        App.daggerMainComponent.inject(this)
        adapter.setView(this)
        super.onAttach(context)
    }

    override fun setClickPosition(clickPosition: Int) {

        val intent = Intent(activity, ImageActivity::class.java)
        intent.putExtra("clickPosition",clickPosition)
        intent.putExtra("imgList", presenter.getImgList())
        startActivity(intent)

    }


    override fun errorSearch() {
        progress_bar.visibility = View.INVISIBLE
        Toast.makeText(context,"Ошибка поиска",Toast.LENGTH_LONG).show()
    }

    override fun setItems(items: ArrayList<Item>) {
        progress_bar.visibility = View.INVISIBLE
        adapter.addItems(items)
        photo_recycler_view.scrollToPosition(presenter.getScrollPosition())
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        if(searchFlag) presenter.setQuery(query)

        if (activity!!.resources.configuration.orientation == HORIZONTAL_ORIENTATION)
            gridLayoutManager  = GridLayoutManager(activity, 4)
        else
            gridLayoutManager = GridLayoutManager(activity, 2)

        photo_recycler_view.layoutManager = gridLayoutManager

        photo_recycler_view.adapter = adapter
        photo_recycler_view.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.setScrollPosition(gridLayoutManager.findLastVisibleItemPosition())

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        super.onActivityCreated(savedInstanceState)
    }

    fun firstSearch(query:String){
        searchFlag = true
        this.query = query
    }

    fun secondSearch(query:String){
        presenter.setQuery(query)
        progress_bar.visibility = View.VISIBLE
    }

}