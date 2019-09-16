package com.art.tattoosearch.repository

import com.art.tattoosearch.PATH_FOR_IMG
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

    fun delSavedPic(name: String): Boolean {
        val delPic = File("$PATH_FOR_IMG/$name")
        return delPic.delete()
    }

    fun createFolder(path: String): Boolean {
        var isCreateFolder = false
        val folder = File(path)
        if (!folder.exists()) {
            isCreateFolder = folder.mkdir()
        } else isCreateFolder = true

        return isCreateFolder
    }
}

