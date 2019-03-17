package com.example.tattoosearch.dagger

import com.example.tattoosearch.PATH_FOR_IMG
import com.example.tattoosearch.models.FolderManager
import com.example.tattoosearch.ui.search.image.ImgAdapter
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
open class ImgModule{


    @Provides
    fun     provideImgAdapter(): ImgAdapter {
        return ImgAdapter()
    }
    @Singleton
    @Provides
    fun provideFolder(): File{
        return File(PATH_FOR_IMG)
    }
    @Singleton
    @Provides
    fun provideFolderManager(file: File):FolderManager{
        return FolderManager(file)
    }
}