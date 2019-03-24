package com.art.tattoosearch.models

import android.os.Environment
import android.util.Log
import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.presenter.ImgInterface
import java.io.File

class FolderManager(private val folder:File) {
    private var savedPics = ArrayList<String>()
    lateinit var presenter: ImgInterface
    init {
        if (!folder.exists()) {

        createFolder()
        }
    }

    fun getSavedPics(): ArrayList<String> {
        savedPics.clear()

        if (!folder.exists()) createFolder()

        else {
            for (file in folder.listFiles()) {
                savedPics.add(file.name)
            }
        }
        return savedPics
    }

    fun setPresenterInterface(presenter: ImgInterface){
        this.presenter = presenter
    }
    fun delSavedPic(id: Int) {

        val delPic = File(PATH_FOR_IMG+"/"+ savedPics[id])
            delPic.delete()

    }

    fun createFolder(){
        val a = folder.mkdir()
        Log.d("FOLDR", Environment.getExternalStorageDirectory().toString() +
                File.separator +
                Environment.DIRECTORY_PICTURES +
                File.separator +
                "Ink"+"   $a")
    }
}

