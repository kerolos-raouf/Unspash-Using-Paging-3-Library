package com.example.testpaging3library.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testpaging3library.data.model.UnsplashImage
import com.example.testpaging3library.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val searchRepository: Repository
) : ViewModel() {

    private val _searchResult = MutableLiveData<PagingData<UnsplashImage>>(PagingData.empty())
    val searchResult: LiveData<PagingData<UnsplashImage>> = _searchResult


    @OptIn(ExperimentalPagingApi::class)
    fun searchForImages(query: String) {
        viewModelScope.launch {
            searchRepository.searchForImages(query).cachedIn(viewModelScope).collect{
                _searchResult.value = it
            }
        }
    }

}