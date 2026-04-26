package com.example.gratzl.data.model

data class ChatMessage(
    val id: Int,
    val chatId: Int,
    val senderId: Int,             // zeigt auf UserProfile.id
    val text: String,
    val timestamp: String,         // z.B. "14:32" oder "Yesterday"
    val isBookingOffer: Boolean = false
)

data class Chat(
    val id: Int,
    val listingId: Int,
    val partnerId: Int,            // UserProfile.id des Chat-Partners
    val messages: List<ChatMessage> = emptyList()
)