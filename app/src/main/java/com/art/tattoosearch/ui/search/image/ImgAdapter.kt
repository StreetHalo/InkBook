package com.art.tattoosearch.ui.search.image

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.tattoosearch.R
import com.art.tattoosearch.models.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_adapter_img.view.*

class ImgAdapter: RecyclerView.Adapter<ImgAdapter.PhotoHolder>() {

    lateinit var listItems:ArrayList<Image>

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PhotoHolder {


        return PhotoHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter_img,parent,false))
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addItems(items:ArrayList<Image>){
        listItems = items
        notifyDataSetChanged()
    }

    fun clearItem(){
        listItems.clear()
    }



    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listItems[position])
    }


    inner class PhotoHolder(itemView: View) : ViewHolder(itemView) {
        fun  setImg(item: Image){


            Picasso
                .with(itemView.context)
                .load(item.imgUrl)

                .into(itemView.item_image_view)


        }
    }
}