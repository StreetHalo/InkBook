package com.art.tattoosearch.ui.search.image

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.art.tattoosearch.App
import com.art.tattoosearch.R
import com.art.tattoosearch.REQUEST_PERMISSIONS_CODE
import com.art.tattoosearch.models.Image
import com.art.tattoosearch.presenter.ImgPresenter
import com.squareup.picasso.Picasso
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.recycler_adapter_img.*
import javax.inject.Inject
import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Target


class ImageActivity : MvpAppCompatActivity(), ImageViewInterface,
    DiscreteScrollView.OnItemChangedListener<ImgAdapter.PhotoHolder>,
        DiscreteScrollView.ScrollStateChangeListener<ImgAdapter.PhotoHolder>
{

    private var listImg = arrayListOf<Image>()
    @Inject
    lateinit var adapter: ImgAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: ImgPresenter

    @ProvidePresenter
    internal fun providePresenter(): ImgPresenter {
        return presenter
    }

    private var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerMainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        listImg =  intent.getParcelableArrayListExtra("imgList")
        position = intent.getIntExtra("clickPosition",0)
        adapter.addItems(listImg)
        presenter.setImgList(listImg)
        presenter.setPosition(position)
        presenter.setButtonTheme()
        setSupportActionBar(toolbar_img)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_img.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_img.setNavigationOnClickListener {
            onBackPressed()
        }


        button_1.setOnClickListener {
            if(checkPermission())
            presenter.saveLikePosition(this,item_image_view)
            else ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSIONS_CODE)

        }

        button_2.setOnClickListener {
            Picasso.with(baseContext)
                .load(listImg[presenter.getPosition()].imgUrl)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                        Toast.makeText(baseContext,"Попробуйте еще раз",Toast.LENGTH_SHORT).show()
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        val path = MediaStore.Images.Media.insertImage(
                            contentResolver,
                            bitmap, "Design", null
                        )
                        val uri = Uri.parse(path)

                        val intent = Intent(Intent.ACTION_SEND)
                        intent.putExtra(Intent.EXTRA_STREAM, uri)
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        intent.type = "image/jpg"
                        startActivity(Intent.createChooser(intent,"Поделись"))

                    }
                })
        }
        picker.addOnItemChangedListener(this)
        picker.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
        picker.adapter = adapter
        picker.scrollToPosition(presenter.getPosition())
        picker.addOnItemChangedListener(this)
        picker.addScrollStateChangeListener(this)

    }

    override fun onCurrentItemChanged(viewHolder: ImgAdapter.PhotoHolder?, adapterPosition: Int) {
        presenter.setPosition(adapterPosition)
        presenter.setButtonTheme()
    }

    override fun setLikeTheme() {
        button_1.setImageResource(R.drawable.ic_save_red_24dp)
        button_1.isActivated = false
    }

    override fun setDislikeTheme() {
        button_1.setImageResource(R.drawable.ic_save_black_24dp)
        button_1.isActivated = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            REQUEST_PERMISSIONS_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter.saveLikePosition(this,item_image_view)

                } else {
                    Toast.makeText(this,"Для работы приложения необходим доступ к чтению и записи данных", Toast.LENGTH_SHORT).show()
                }
                return
            }

        }

    }



    private fun checkPermission():Boolean{
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            return false
        }

        return true
    }
    override fun onScrollEnd(currentItemHolder: ImgAdapter.PhotoHolder, adapterPosition: Int) {
        button_1.visibility = View.VISIBLE
        button_2.visibility = View.VISIBLE
    }

    override fun onScrollStart(currentItemHolder: ImgAdapter.PhotoHolder, adapterPosition: Int) {
        button_1.visibility = View.INVISIBLE
        button_2.visibility = View.INVISIBLE
    }

    override fun onScroll(
        scrollPosition: Float,
        currentPosition: Int,
        newPosition: Int,
        currentHolder: ImgAdapter.PhotoHolder?,
        newCurrent: ImgAdapter.PhotoHolder?
    ) {
    }

}
