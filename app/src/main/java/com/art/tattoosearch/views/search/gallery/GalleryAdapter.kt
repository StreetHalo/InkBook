package com.art.tattoosearch.views.search.gallery


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art.tattoosearch.R
import com.art.tattoosearch.entities.Item
import kotlinx.android.synthetic.main.gallery_card.view.*
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.PhotoHolder>() {

    var listItems: ArrayList<Item> = ArrayList()
    lateinit var viewInterface: SearchViewInterface
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_card, parent, false)
        val holder = PhotoHolder(view)

        view.setOnClickListener {
            viewInterface.setClickPosition(holder.adapterPosition)
        }
        return holder
    }

    fun setView(viewInterface: SearchViewInterface) {
        this.viewInterface = viewInterface
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun addItems(items: ArrayList<Item>) {
        listItems = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.setImg(listItems[position])
    }

    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setImg(item: Item) {

            Picasso
                .with(itemView.context)
                .load(item.link)
                .transform(object : RoundedCornersTransformation(15, 0) {
                })
                .into(itemView.card_img)


        }
    }
}