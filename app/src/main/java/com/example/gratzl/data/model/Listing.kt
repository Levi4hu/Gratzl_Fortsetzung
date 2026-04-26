package com.example.gratzl.data.model

data class Listing(
    val id: Int,
    val title: String,
    val description: String,
    val isOffer: Boolean,          // true = Angebot, false = Anfrage
    val category: String,
    val district: String,
    val priceType: PriceType,
    val price: Double? = null,     // null wenn FREE oder TRADE
    val urgency: UrgencyTag = UrgencyTag.FLEXIBLE,
    val userId: Int,
    val imageRes: Int? = null      // R.drawable.xyz oder null
)

enum class PriceType {
    FIXED,       // fester Preis
    PER_HOUR,    // pro Stunde
    FREE,        // kostenlos
    TRADE,       // Tausch
    COFFEE       // Kaffee :)
}

enum class UrgencyTag {
    FLEXIBLE,
    TODAY,
    URGENT
}