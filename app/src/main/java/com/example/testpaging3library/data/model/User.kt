package com.example.testpaging3library.data.model

import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userName : String,
    @Embedded
    val links : UserLinks
)
