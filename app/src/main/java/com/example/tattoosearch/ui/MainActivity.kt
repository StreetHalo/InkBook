package com.example.tattoosearch.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.tattoosearch.*
import com.example.tattoosearch.ui.favorite.gallery.FavFragment
import com.example.tattoosearch.ui.search.gallery.GalleryFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import android.R.id.edit
import android.content.SharedPreferences
import android.content.DialogInterface
import android.text.Html
import android.widget.CheckBox
import android.R.id.checkbox
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater



class MainActivity : MvpAppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val searchGalleryFragment : GalleryFragment = GalleryFragment()
    private val favGalleryFragment : FavFragment = FavFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.daggerMainComponent.inject(this)

    }

    override fun onResume() {
        super.onResume()
        if(checkPermission()) {
            setToolbar()
        } else
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSIONS_CODE)
        setOnLineTheme()


        search_view.setOnQueryTextListener(object :MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if(isConnected()) {
                        if (!searchGalleryFragment.isAdded) {
                            setFragment(searchGalleryFragment)
                            searchGalleryFragment.firstSearch(query)
                        } else searchGalleryFragment.secondSearch(query)
                    }
                    else Toast.makeText(baseContext,"Необходимо подключение к Интернету",Toast.LENGTH_SHORT).show()
                }
                search_view.closeSearch()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main_activity,menu)
        val itemSearch = menu!!.findItem(R.id.search)
        search_view.setMenuItem(itemSearch)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item.toString()){

            FAVORITE_ITEM -> {
            if(!favGalleryFragment.isAdded)
                setFragment(favGalleryFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {

            REQUEST_PERMISSIONS_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    setToolbar()
                } else {
                    Toast.makeText(this,"Для работы приложения необходим доступ к чтению и записи данных",Toast.LENGTH_SHORT).show()
                }
                return
            }

        }
    }

    private fun setOnLineTheme(){
        background_pic.setImageResource(R.drawable.back)
        background_pic.isEnabled = false
        background_pic.alpha = 0.2f
    }

    private fun isConnected(): Boolean {
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun setFragment(fragment :Fragment){
        if(favGalleryFragment.isAdded) fragmentManager.beginTransaction().remove(favGalleryFragment).commit()
        if(searchGalleryFragment.isAdded) fragmentManager.beginTransaction().remove(searchGalleryFragment).commit()

        fragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun checkPermission():Boolean{
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
                return false
        }

        return true
    }

    private fun setToolbar(){
        toolbar.setNavigationIcon(com.example.tattoosearch.R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
