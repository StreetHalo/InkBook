package com.example.tattoosearch.presenter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.tattoosearch.App
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.models.Image
import com.example.tattoosearch.models.SavingImg
import com.example.tattoosearch.ui.search.image.ImageViewInterface
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import javax.inject.Inject

@InjectViewState
class ImgPresenter:MvpPresenter<ImageViewInterface>(),ImgInterface {

    @Inject
    lateinit var folderManager: FolderManager
    private var position = 0
    private var listImg  = ArrayList<Image>()

    init {
        App.daggerMainComponent.inject(this)
    }


    override fun setPosition(position: Int) {
        this.position = position

    }

    fun setButtonTheme(){
        if( listImg[position].isLiked == 1)
                viewState.setLikeTheme()
        else viewState.setDislikeTheme()

    }

    override fun getPosition(): Int {
        return position
    }

    override fun saveImg() {
    }

    fun setImgList(listImg:ArrayList<Image>){
        this.listImg = listImg
    }


    fun saveLikePosition(context: Context, imageView: ImageView) {
        listImg[position].isLiked = 1
        viewState.setLikeTheme()
            Picasso.with(context)
            .load(listImg[position].imgUrl)
            .into(object :SavingImg(listImg[position].thumbnailLink, imageView){})
        Toast.makeText(context,"Картинка сохранена",Toast.LENGTH_SHORT).show()
    }

    override fun delImg() {
    }
}