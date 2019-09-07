package com.art.tattoosearch.presenter

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.art.tattoosearch.models.FolderManager
import com.art.tattoosearch.models.Image
import com.art.tattoosearch.models.SavingImg
import com.art.tattoosearch.views.search.image.ImageViewInterface
import com.squareup.picasso.Picasso

@InjectViewState
class ImgPresenter(val folderManager: FolderManager) :MvpPresenter<ImageViewInterface>(),ImgInterface {

    private var position = 0
    private var listImg  = ArrayList<Image>()



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