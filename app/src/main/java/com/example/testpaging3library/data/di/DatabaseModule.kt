package com.example.testpaging3library.data.di

import android.content.Context
import androidx.room.Room
import com.example.testpaging3library.data.database.UnsplashDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "unsplash_database"


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UnsplashDatabase {
        return Room.databaseBuilder(
            context,
            UnsplashDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideInt(@ApplicationContext context: Context) : Int {
        context.getSharedPreferences("test",Context.MODE_PRIVATE)
        return 1000
    }

    @Provides
    @Singleton
    fun provideString() : String = "this is string from database module"

}