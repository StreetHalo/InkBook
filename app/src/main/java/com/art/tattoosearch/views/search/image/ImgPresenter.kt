package com.art.tattoosearch.views.search.image

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.repository.FolderManager
import com.art.tattoosearch.entities.ImageForView
import com.art.tattoosearch.use_cases.SavingImg
import com.squareup.picasso.Picasso

@InjectViewState
class ImgPresenter(val context: Context) : MvpPresenter<ImageViewInterface>() {

    private var position = 0
    private var listImg = ArrayList<ImageForView>()

    fun setPosition(position: Int) {
        this.position = position
    }

    fun setButtonTheme() {
        if (listImg[position].isLiked == 1)
            viewState.setLikeTheme()
        else viewState.setDislikeTheme()
    }

    fun getPosition(): Int {
        return position
    }

    fun setImgList(listImg: ArrayList<ImageForView>) {
        this.listImg = listImg
    }

    fun saveLikePosition(imageView: ImageView) {
        listImg[position].isLiked = 1
        viewState.setLikeTheme()
        Picasso.with(context)
            .load(listImg[position].imgUrl)
            .into(object :
                SavingImg(listImg[position].thumbnailLink, imageView) {})
        Toast.makeText(context, "Картинка сохранена", Toast.LENGTH_SHORT).show()
    }
}