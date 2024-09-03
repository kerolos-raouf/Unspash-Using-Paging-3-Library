package com.example.testpaging3library.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testpaging3library.data.database.dao.UnsplashImageDao
import com.example.testpaging3library.data.database.dao.UnsplashRemoteKeysDao
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.model.UnsplashImageKeys


@Database(entities = [UnsplashImage::class, UnsplashImageKeys::class], version = 1)
abstract class UnsplashDatabase  : RoomDatabase()
{

    abstract fun unsplashImageDao() : UnsplashImageDao
    abstract fun unsplashRemoteKeysDao() : UnsplashRemoteKeysDao

}