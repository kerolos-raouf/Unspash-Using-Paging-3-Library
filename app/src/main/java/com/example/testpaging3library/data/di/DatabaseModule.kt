package com.example.testpaging3library.data.di

import android.content.Context
import androidx.room.Room
import com.example.testpaging3library.data.database.UnsplashDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DATABASE_NAME = "unsplash_database"


    fun provideUnsplashDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        UnsplashDatabase::class.java,
        DATABASE_NAME)
        .build()




}