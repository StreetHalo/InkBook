package com.art.tattoosearch.entities

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class ImageForView(var imgUrl:String, var isLiked:Int, val thumbnailLink:String) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
        )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(imgUrl)
        dest.writeInt(isLiked)
        dest.writeString(thumbnailLink)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageForView> {
        override fun createFromParcel(parcel: Parcel): ImageForView {
            return ImageForView(parcel)
        }

        override fun newArray(size: Int): Array<ImageForView?> {
            return arrayOfNulls(size)
        }
    }
}