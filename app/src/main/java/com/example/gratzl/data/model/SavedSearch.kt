package com.example.gratzl.data.model

data class SavedSearch(
    val id: Int,
    val label: String,
    val categoryIcon: String,
    val query: String,
    val category: String?,
    val isOffer: Boolean,
    val priceType: PriceType? = null,
    val urgency: UrgencyTag? = null
)