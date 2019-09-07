package com.art.tattoosearch.models

import android.os.Environment
import android.util.Log
import com.art.tattoosearch.PATH_FOR_IMG
import com.art.tattoosearch.presenter.ImgInterface
import java.io.File

class FolderManager() {
    private var savedPics = ArrayList<String>()

    fun getSavedPics(): ArrayList<String> {
        savedPics.clear()
        val folder = File(PATH_FOR_IMG)

        if (!folder.exists()) createFolder(PATH_FOR_IMG)

        else {
            for (file in folder.listFiles()) {
                savedPics.add(file.name)
            }
        }
        return savedPics
    }

    fun delSavedPic(id: Int) {

        val delPic = File(PATH_FOR_IMG+"/"+ savedPics[id])
            delPic.delete()
    }

    fun createFolder(path:String): Boolean {
        var isCreateFolder = false
        val folder = File(path)
        if (!folder.exists()) {
            isCreateFolder = folder.mkdir()
        }
        else isCreateFolder = true

        return isCreateFolder

    }
}

