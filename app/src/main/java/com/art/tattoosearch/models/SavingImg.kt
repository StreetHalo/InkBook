package com.art.tattoosearch.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream

open class SavingImg(var name :String, private val imageView: ImageView ):Target {

        init {
            name = name.substring(name.indexOf("=tbn:")+5)
        }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

    }

    override fun onBitmapFailed(errorDrawable: Drawable?) {

    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        val file = File(
            Environment.getExternalStorageDirectory().toString() +
                    File.separator +
                    Environment.DIRECTORY_PICTURES +
                    File.separator +
                    "Ink" +
                    File.separator
                    + name + ".jpg"
        )

        try {
            file.createNewFile()
            val ostream = FileOutputStream(file)
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, ostream)
            }
            ostream.close()
            imageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }    }
}