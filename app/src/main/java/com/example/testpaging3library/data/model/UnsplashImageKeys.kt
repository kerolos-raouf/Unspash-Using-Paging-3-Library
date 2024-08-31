package com.example.testpaging3library.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class UnsplashImageKeys(
    @PrimaryKey
    val id : String,
    val prevPage : Int?,
    val nextPage : Int?

)
