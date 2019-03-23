package com.example.tattoosearch.ui.favorite.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.HORIZONTAL_ORIENTATION
import com.example.tattoosearch.R
import com.example.tattoosearch.presenter.FavPresenter
import com.example.tattoosearch.ui.favorite.item.ImageFavActivity
import kotlinx.android.synthetic.main.fav_gallery_fragment.*
import javax.inject.Inject
import android.view.*
import android.view.MenuInflater
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.tattoosearch.ui.MainActivity


class FavFragment: MvpAppCompatFragment(), FavViewInterface {

    @Inject
    lateinit var adapter: FavAdapter
    @Inject
    @InjectPresenter
    lateinit var presenter: FavPresenter

    @ProvidePresenter
    internal fun providePresenter(): FavPresenter {
        return presenter
    }


    lateinit var  gridLayoutManager:GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fav_gallery_fragment,container,false)

    }

    override fun onAttach(context: Context?) {
        App.daggerMainComponent.inject(this)
        super.onAttach(context)
        adapter.setView(this)
        setHasOptionsMenu(true)

    }



    override fun onResume() {
        super.onResume()
        presenter.updateAdapter()
        (activity as MainActivity).supportActionBar!!.title = "Избранное"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        fav_img.alpha = 0.2f

        gridLayoutManager = if (activity!!.resources.configuration.orientation == HORIZONTAL_ORIENTATION)
            GridLayoutManager(activity, 4)
        else
            GridLayoutManager(activity, 2)

        photo_recycler_view.layoutManager = gridLayoutManager
        photo_recycler_view.adapter = adapter
        photo_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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


    override fun setItems(items: ArrayList<String>) {
        progress_bar.visibility = View.INVISIBLE
        if(items.size>0) fav_img.visibility = View.INVISIBLE
        adapter.addItems(items)
        photo_recycler_view.scrollToPosition(presenter.getScrollPosition())
    }


    override fun setClickPosition(clickPosition: Int) {

        val intent = Intent(activity, ImageFavActivity::class.java)
        intent.putExtra("clickSavedPosition",clickPosition)
        intent.putExtra("imgSavedList", presenter.getSavedList())
        startActivity(intent)
    }

    override fun emptyFolder() {
     //   Toast.makeText(context,"У вас нет сохраненных изображений",Toast.LENGTH_SHORT).show()
        fav_img.visibility = View.VISIBLE
    }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       menu.findItem(R.id.favorite).isVisible = false
       menu.findItem(R.id.search).isVisible = false
       super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDetach() {
        (activity as MainActivity).supportActionBar!!.title = "InkBook"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar!!.setDisplayShowHomeEnabled(false)
        super.onDetach()
    }
}