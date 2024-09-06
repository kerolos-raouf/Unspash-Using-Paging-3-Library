package com.example.testpaging3library.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testpaging3library.BuildConfig
import com.example.testpaging3library.data.database.UnsplashDatabase
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.network.UnsplashApi
import com.example.testpaging3library.data.paging.SearchPagingSource
import com.example.testpaging3library.data.paging.UnsplashRemoteMediator
import com.example.testpaging3library.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
){

    fun getAllImages() : Flow<PagingData<UnsplashImage>>
    {
        return Pager(
            config = PagingConfig(pageSize = Constants.IMAGES_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi =unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        ).flow
    }


    fun searchForImages(query : String) : Flow<PagingData<UnsplashImage>>
    {
        return Pager(
            config = PagingConfig(pageSize = Constants.IMAGES_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow
    }



}