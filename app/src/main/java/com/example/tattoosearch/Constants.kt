package com.example.tattoosearch

import android.os.Environment
import java.io.File

const val SEARCH_ITEM = "Поиск"

const val FAVORITE_ITEM = "Избранное"


const val REQUEST_PERMISSIONS_CODE  = 321

 const val URL = "https://www.googleapis.com/customsearch/"


const val SEARCH_TYPE = "image"

const val SEARCH_GALLERY_FRAGMENT = "search"

const val HORIZONTAL_ORIENTATION = 2

const val VERTICAL_ORIENTATION = 1

const val IMAGE_SIZE = "xlarge"  // icon, small, medium, large, xlarge, xxlarge, and huge

const val SEARCH_WORD = "tattoo "

 var PATH_FOR_IMG = Environment.getExternalStorageDirectory().toString() +
         File.separator + Environment.DIRECTORY_PICTURES +
         File.separator +
         "Ink"