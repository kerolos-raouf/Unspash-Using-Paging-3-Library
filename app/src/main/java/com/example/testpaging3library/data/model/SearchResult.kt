package com.example.testpaging3library.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val results : List<UnsplashImage>
)
