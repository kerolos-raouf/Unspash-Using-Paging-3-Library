package com.example.testpaging3library.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.testpaging3library.data.database.UnsplashDatabase
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.model.UnsplashImageKeys
import com.example.testpaging3library.data.network.UnsplashApi
import com.example.testpaging3library.util.Constants
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImage>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
         return try {
             val currentPage = when(loadType)
             {
                 LoadType.REFRESH ->
                 {
                     Log.d("Kerolos", "load: refresh")
                     val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                     remoteKeys?.nextPage?.minus(1) ?: 1
                 }
                 LoadType.PREPEND ->
                 {
                     Log.d("Kerolos", "load: prepend")
                     val remoteKeys = getRemoteKeyForFirstItem(state)
                     val prevPage = remoteKeys?.prevPage
                         ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                     prevPage
                 }
                 LoadType.APPEND ->
                 {
                     Log.d("Kerolos", "load: append")
                     val remoteKeys = getRemoteKeyForLastItem(state)
                     val nextPage = remoteKeys?.nextPage
                         ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                     nextPage
                 }
             }

             val response  = unsplashApi.getAllImages(currentPage,Constants.IMAGES_PER_PAGE)

             val endOfPaginationReached = response.isEmpty()

             val prevPage = if(currentPage == 1)  null else currentPage - 1
             val nextPage = if(endOfPaginationReached) null else currentPage + 1

             unsplashDatabase.withTransaction {
                 if(loadType == LoadType.REFRESH)
                 {
                     unsplashDatabase.unsplashRemoteKeysDao().deleteRemoteKeys()
                     unsplashDatabase.unsplashImageDao().deleteAllImages()
                 }

                 val keys = response.map {
                     UnsplashImageKeys(
                         id = it.id,
                         prevPage = prevPage,
                         nextPage = nextPage
                     )
                 }

                 unsplashDatabase.unsplashRemoteKeysDao().insertRemoteKeys(keys)
                 unsplashDatabase.unsplashImageDao().insertImages(response)


             }

             MediatorResult.Success(endOfPaginationReached)
         }catch (e : Exception)
         {
             Log.d("Kerolos", "load: ${e.message}")
             MediatorResult.Error(e)
         }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UnsplashImage>) : UnsplashImageKeys?
    {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                unsplashDatabase.unsplashRemoteKeysDao().getRemoteKeys(it)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UnsplashImage>) : UnsplashImageKeys?
    {
        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()?.let {
            unsplashDatabase.unsplashRemoteKeysDao().getRemoteKeys(it.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UnsplashImage>) : UnsplashImageKeys?
    {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()?.let {
            unsplashDatabase.unsplashRemoteKeysDao().getRemoteKeys(it.id)
        }
    }



}