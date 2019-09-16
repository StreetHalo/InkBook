package com.art.tattoosearch.views.search.image

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.art.tattoosearch.App
import com.art.tattoosearch.R
import com.art.tattoosearch.entities.ImageForView
import com.squareup.picasso.Picasso
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.img_card.*
import javax.inject.Inject
import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Target
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class ImageActivity : MvpAppCompatActivity(), ImageViewInterface,
    DiscreteScrollView.OnItemChangedListener<ImgAdapter.PhotoHolder>,
    DiscreteScrollView.ScrollStateChangeListener<ImgAdapter.PhotoHolder> {

    private var listImg = arrayListOf<ImageForView>()
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
    private val compositeDisposable = CompositeDisposable()
    private val rxPermissions = RxPermissions(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerMainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        listImg = intent.getParcelableArrayListExtra("imgList")
        position = intent.getIntExtra("clickPosition", 0)
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
            val permission = rxPermissions
                .request(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { granted ->

                    presenter.saveLikePosition(item_image_view)

                }
            compositeDisposable.add(permission)

        }

        button_2.setOnClickListener {
            Picasso.with(baseContext)
                .load(listImg[presenter.getPosition()].imgUrl)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                        Toast.makeText(baseContext, "Попробуйте еще раз", Toast.LENGTH_SHORT).show()
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
                        startActivity(Intent.createChooser(intent, "Поделись"))

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
