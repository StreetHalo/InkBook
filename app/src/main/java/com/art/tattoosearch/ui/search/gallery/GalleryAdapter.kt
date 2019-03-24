package com.art.tattoosearch.ui.search.gallery


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.art.tattoosearch.R
import com.art.tattoosearch.jsonModel.Item
import kotlinx.android.synthetic.main.recycler_adapter.view.*
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.PhotoHolder>() {

    var  listItems :  ArrayList<Item> = ArrayList()
    lateinit var viewInterface: SearchViewInterface

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter,parent,false)
        val holder = PhotoHolder(view)

        view.setOnClickListener {
            viewInterface.setClickPosition(holder.adapterPosition)
        }
            return holder
    }

    fun setView(viewInterface: SearchViewInterface){
        this.viewInterface = viewInterface
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addItems(items:ArrayList<Item>){
        listItems = items
        notifyDataSetChanged()
    }

    fun clearItem(){
        listItems.clear()
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listItems[position])
    }

    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         fun  setImg(item:Item){

             Picasso
                 .with(itemView.context)
                 .load(item.link)
                 .transform(object : RoundedCornersTransformation(10,0) {
                 })
                 .into(itemView.item_image_view)


         }
    }
}