package com.example.gratzl.data.model

data class UserProfile(
    val id: Int,
    val name: String,
    val district: String,
    val bio: String = "",
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val avatarRes: Int? = null,
    val radius: Int = 5,
    val skills: List<UserSkill> = emptyList(),
    val responseTime: String = "< 2h",
    val responseSpeedLabel: String = "SEHR SCHNELL",
    val availableDayNumbers: List<Int> = listOf(22, 24, 25, 28)
)

data class UserSkill(
    val title: String,
    val description: String = ""
)