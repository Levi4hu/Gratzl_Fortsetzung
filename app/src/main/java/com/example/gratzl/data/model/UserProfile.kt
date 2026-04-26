package com.example.gratzl.data.model

data class UserProfile(
    val id: Int,
    val name: String,
    val district: String,
    val bio: String = "",
    val rating: Float = 0f,        // 0.0 bis 5.0
    val avatarRes: Int? = null,    // R.drawable.xyz oder null
    val radius: Int = 5,           // Einsatzradius in km
    val skills: String = ""        // kommagetrennt
)