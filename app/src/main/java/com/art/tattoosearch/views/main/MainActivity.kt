package com.art.tattoosearch.views.main


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.art.tattoosearch.*
import com.art.tattoosearch.views.favorite.gallery.FavFragment
import com.art.tattoosearch.views.search.gallery.GalleryFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainActivityInterface {

    private val fragmentManager = supportFragmentManager
    private val searchGalleryFragment: GalleryFragment = GalleryFragment()
    private val favGalleryFragment: FavFragment = FavFragment()

    @Inject
    lateinit var presenter: MainActivityPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerMainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.setQuery(query)
                search_view.closeSearch()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main_activity, menu)
        val itemSearch = menu!!.findItem(R.id.search)
        search_view.setMenuItem(itemSearch)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        presenter.itemSelected(item.toString())
        return super.onOptionsItemSelected(item)
    }


    override fun needConnect() {
        Toast.makeText(baseContext, "Необходимо подключение к Интернету", Toast.LENGTH_SHORT).show()
    }

    override fun setSearchFragment(query: String) {

        val fragment = fragmentManager.findFragmentByTag("search_fragment")
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commit()
        }
        val bundle = Bundle()
        bundle.putString("query", query)
        searchGalleryFragment.arguments = bundle

        fragmentManager.beginTransaction()
            .replace(R.id.fragment, searchGalleryFragment, "search_fragment")
            .addToBackStack(null)
            .commit()
    }

    override fun setFavFragment() {
        val fragment = fragmentManager.findFragmentByTag("search_fragment")
        if (fragment != null) fragmentManager.beginTransaction().remove(fragment).commit()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment, favGalleryFragment, "search_fragment")
            .addToBackStack(null)
            .commit()
    }


    private fun setToolbar() {
        toolbar.setNavigationIcon(com.art.tattoosearch.R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
