package com.example.testpaging3library.data.model

import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username : String = "Kerolos",
    @Embedded
    val links : UserLinks
)
