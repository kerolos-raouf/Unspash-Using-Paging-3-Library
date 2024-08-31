package com.example.testpaging3library.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testpaging3library.data.model.UnsplashImage

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM unsplashimage")
    suspend fun getAllImages() : PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images : List<UnsplashImage>)


    @Query("DELETE FROM unsplashimage")
    suspend fun deleteAllImages()

}