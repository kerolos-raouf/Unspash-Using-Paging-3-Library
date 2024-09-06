package com.example.testpaging3library.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.testpaging3library.data.repository.Repository
import com.example.testpaging3library.data.repository.TestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
)
: ViewModel() {

    val getAllImages = repository.getAllImages().cachedIn(viewModelScope)
}