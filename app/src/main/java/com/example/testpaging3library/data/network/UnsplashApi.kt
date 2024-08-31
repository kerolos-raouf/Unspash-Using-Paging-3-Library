package com.example.testpaging3library.data.network

import com.example.testpaging3library.BuildConfig
import com.example.testpaging3library.data.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    companion object
    {
        private const val API_KEY = "LKNWqBHwc8hHAicK_giAbNkr-8yv5A7asu86kLsZH8Q"
    }
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("photos")
    suspend fun getAllImages(
        @Query("page") page : Int,
        @Query("per_page") perPage : Int
    ) : List<UnsplashImage>

}