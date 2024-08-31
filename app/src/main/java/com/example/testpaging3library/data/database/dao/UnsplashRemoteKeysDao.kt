package com.example.testpaging3library.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testpaging3library.data.model.UnsplashImageKeys

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplashimagekeys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashImageKeys


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<UnsplashImageKeys>)

    @Query("DELETE FROM unsplashimagekeys")
    suspend fun deleteRemoteKeys()
}