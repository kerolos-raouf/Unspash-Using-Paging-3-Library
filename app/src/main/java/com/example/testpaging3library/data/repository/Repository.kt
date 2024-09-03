package com.example.testpaging3library.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testpaging3library.data.database.UnsplashDatabase
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.network.UnsplashApi
import com.example.testpaging3library.data.paging.UnsplashRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
){

    fun getAllImages() : Flow<PagingData<UnsplashImage>>
    {
        val pagingSourceFactory = {
            unsplashDatabase.unsplashImageDao().getAllImages()
        }

        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi =unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getString() = "this is data from repository"



}