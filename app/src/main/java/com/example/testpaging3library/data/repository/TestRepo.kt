package com.example.testpaging3library.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import com.example.testpaging3library.data.database.UnsplashDatabase
import com.example.testpaging3library.data.network.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRepo @Inject constructor(
    unsplashApi: UnsplashApi,
    unsplashDatabase: UnsplashDatabase
){
    fun getString() = "this is data from repository"
    init {
        //Log.d("Kerolos", "TestRepo $str")
    }
}