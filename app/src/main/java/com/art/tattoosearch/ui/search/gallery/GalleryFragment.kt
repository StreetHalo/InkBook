package com.art.tattoosearch.ui.search.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.tattoosearch.R
import kotlinx.android.synthetic.main.gallery_fragment.*
import android.support.v7.widget.RecyclerView
import android.text.method.LinkMovementMethod
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.art.tattoosearch.App
import com.art.tattoosearch.HORIZONTAL_ORIENTATION
import com.art.tattoosearch.jsonModel.Item
import com.art.tattoosearch.presenter.SearchPresenter
import com.art.tattoosearch.ui.search.image.ImageActivity
import javax.inject.Inject
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView


class GalleryFragment : MvpAppCompatFragment(), SearchViewInterface {

    private val TAG = this.javaClass.simpleName
    private var searchFlag = false
    lateinit var  gridLayoutManager:GridLayoutManager

    @Inject
    lateinit var adapter: GalleryAdapter
    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    internal fun providePresenter(): SearchPresenter {
        return presenter
    }

    var query = "null"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.gallery_fragment,container,false)
    }

    override fun onAttach(context: Context?) {
        App.daggerMainComponent.inject(this)
        adapter.setView(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun setClickPosition(clickPosition: Int) {

        val intent = Intent(activity, ImageActivity::class.java)
        intent.putExtra("clickPosition",clickPosition)
        intent.putExtra("imgList", presenter.getImgList())
        startActivity(intent)

    }


    override fun errorSearch() {
        progress_bar.visibility = View.INVISIBLE
        val message =  TextView(context);
        message.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        message.setPadding(40,40,40,40)
        message.gravity = Gravity.LEFT
        message.text = context!!.getText(R.string.oops);
        message.movementMethod = LinkMovementMethod.getInstance();

        val builder = AlertDialog.Builder(this.context!!)
        builder.setTitle("Внимание!")
        builder.setView(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
         builder.create()
        builder.show()

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