package com.art.tattoosearch.views.favorite.gallery

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.gallery_card.view.*
import java.io.File

class FavAdapter : RecyclerView.Adapter<FavAdapter.PhotoHolder>() {

    lateinit var listSaveItems: ArrayList<String>
    lateinit var viewInterface: FavViewInterface


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PhotoHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_card, parent, false)
        val holder = PhotoHolder(view)

        view.setOnClickListener {
            viewInterface.setClickPosition(holder.adapterPosition)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return listSaveItems.size
    }

    fun setView(viewInterface: FavViewInterface) {
        this.viewInterface = viewInterface
    }

    fun addItems(saveItems: ArrayList<String>) {
        listSaveItems = saveItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listSaveItems[position])
    }


    inner class PhotoHolder(itemView: View) : ViewHolder(itemView) {
        fun setImg(img: String) {
            Picasso
                .with(itemView.context)
                .load(object : File("$PATH_FOR_IMG/$img") {})
                .transform(object : RoundedCornersTransformation(10, 0) {
                })
                .into(itemView.card_img)
        }
    }
}