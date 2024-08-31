package com.example.testpaging3library.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UnsplashImage(
    @PrimaryKey
    val id : String,
    val description : String?,
    @Embedded
    val urls : Urls,
    val likes : Int,
    @Embedded
    val user : User
)
