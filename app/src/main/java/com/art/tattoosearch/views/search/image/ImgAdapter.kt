package com.art.tattoosearch.views.search.image

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.tattoosearch.R
import com.art.tattoosearch.entities.ImageForView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.img_card.view.*

class ImgAdapter : RecyclerView.Adapter<ImgAdapter.PhotoHolder>() {

    lateinit var listItems: ArrayList<ImageForView>

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PhotoHolder {


        return PhotoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.img_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addItems(items: ArrayList<ImageForView>) {
        listItems = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listItems[position])
    }


    inner class PhotoHolder(itemView: View) : ViewHolder(itemView) {
        fun setImg(item: ImageForView) {


            Picasso
                .with(itemView.context)
                .load(item.imgUrl)

                .into(itemView.item_image_view)


        }
    }
}