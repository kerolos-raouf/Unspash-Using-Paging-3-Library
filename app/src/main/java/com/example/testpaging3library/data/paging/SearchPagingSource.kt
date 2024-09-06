package com.example.testpaging3library.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testpaging3library.data.database.UnsplashDatabase
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.network.UnsplashApi
import com.example.testpaging3library.util.Constants

class SearchPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.searchForImage(query = query, perPage = Constants.IMAGES_PER_PAGE)
            val endOfPaginationReached = response.results.isEmpty()
            if(response.results.isNotEmpty())
            {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage.minus(1),
                    nextKey = if (endOfPaginationReached) null else currentPage.plus(1)
                )
            }else
            {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch (e : Exception)
        {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }


}