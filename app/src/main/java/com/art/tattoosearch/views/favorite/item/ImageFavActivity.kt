package com.art.tattoosearch.views.favorite.item

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.art.tattoosearch.App
import com.art.tattoosearch.R
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.activity_image.*
import javax.inject.Inject
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.art.tattoosearch.PATH_FOR_IMG
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File


class ImageFavActivity : MvpAppCompatActivity(), ImgFavInterface,
    DiscreteScrollView.OnItemChangedListener<ImgFavAdapter.PhotoHolder>
    , DiscreteScrollView.ScrollStateChangeListener<ImgFavAdapter.PhotoHolder> {


    @Inject
    lateinit var adapter: ImgFavAdapter
    private var listImg = arrayListOf<String>()
    @Inject
    @InjectPresenter
    lateinit var presenter: FavItemPresenter

    @ProvidePresenter
    internal fun providePresenter(): FavItemPresenter {
        return presenter
    }

    private var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerMainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        button_1.setImageResource(R.drawable.ic_delete_black_24dp)

        listImg = intent.getStringArrayListExtra("imgSavedList")
        position = intent.getIntExtra("clickSavedPosition", 0)
        adapter.addItems(listImg)
        presenter.setImgList(listImg)
        presenter.setPosition(position)
        setSupportActionBar(toolbar_img)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_img.setNavigationIcon(com.art.tattoosearch.R.drawable.ic_arrow_back_white_24dp)
        toolbar_img.setNavigationOnClickListener {
            onBackPressed()
        }
        button_1.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Удалить изображение?")
                .setCancelable(false)
                .setPositiveButton("Да") { _, _ ->
                    removeImg()
                }
                .setNegativeButton(
                    "Нет"
                ) { dialog, _ -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }

        button_2.setOnClickListener {
            Picasso.with(baseContext)
                .load(object : File("$PATH_FOR_IMG/${listImg[presenter.getPosition()]}") {})
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
        picker.addScrollStateChangeListener(this)
        picker.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
        picker.adapter = adapter
        picker.scrollToPosition(presenter.getPosition())
    }

    private fun removeImg() {
        presenter.delImg()
    }

    override fun updateAdapter(scrollPosition: Int) {
        if (adapter.getListSize() == 1) onBackPressed()
        else adapter.removeItem(scrollPosition)
    }


    override fun fileIsNotDel() {
        Toast.makeText(this, "Не удалось удалить файл:(\nПопробуйте еще раз", Toast.LENGTH_SHORT)
            .show()
    }

    override fun delIsError() {
        Toast.makeText(this, "Ошибка удаления файла\nПопробуйте еще раз", Toast.LENGTH_SHORT).show()
    }

    override fun onCurrentItemChanged(
        viewHolder: ImgFavAdapter.PhotoHolder?,
        adapterPosition: Int
    ) {
        presenter.setPosition(adapterPosition)
        position = adapterPosition
    }

    override fun onScroll(
        scrollPosition: Float,
        currentPosition: Int,
        newPosition: Int,
        currentHolder: ImgFavAdapter.PhotoHolder?,
        newCurrent: ImgFavAdapter.PhotoHolder?
    ) {
    }

    override fun onScrollEnd(currentItemHolder: ImgFavAdapter.PhotoHolder, adapterPosition: Int) {
        visibleButtons()
    }

    override fun onScrollStart(currentItemHolder: ImgFavAdapter.PhotoHolder, adapterPosition: Int) {
        invisibleButtons()
    }

    fun visibleButtons() {
        button_1.visibility = View.VISIBLE
        button_2.visibility = View.VISIBLE

    }

    fun invisibleButtons() {
        button_1.visibility = View.INVISIBLE
        button_2.visibility = View.INVISIBLE

    }
}
