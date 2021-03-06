package com.art.tattoosearch.views.favorite.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.img_card.view.*
import java.io.File

class ImgFavAdapter : RecyclerView.Adapter<ImgFavAdapter.PhotoHolder>() {

    lateinit var listItems: ArrayList<String>

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

    fun addItems(items: ArrayList<String>) {
        listItems = items
        notifyDataSetChanged()
    }

    fun getListSize(): Int {
        return listItems.size
    }

    fun removeItem(position: Int) {
        listItems.removeAt(position)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listItems[position])
    }


    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setImg(item: String) {


            Picasso
                .with(itemView.context)
                .load(object : File("$PATH_FOR_IMG/$item") {})
                .into(itemView.item_image_view)
        }
    }
}