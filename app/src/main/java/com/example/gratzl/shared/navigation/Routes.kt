package com.example.gratzl.shared.navigation

object Routes {
    const val HOME    = "home"
    const val SEARCH  = "search"
    const val ADD_NEW = "addnew"
    const val CHAT    = "chat"
    const val PROFILE = "profile"

    const val ABOUT      = "about"
    const val ONBOARDING = "onboarding"

    const val SAVED_LISTINGS = "saved-listings"

    // Detail-Screens mit Parametern:
    const val LISTING_DETAIL = "listing/{listingId}"
    const val CHAT_DETAIL    = "chat/{chatId}"
    const val PROFILE_DETAIL = "profile/{userId}"

    // Hilfsfunktionen um Routen mit IDs zu bauen:
    fun listingDetail(id: Int) = "listing/$id"
    fun chatDetail(id: Int)    = "chat/$id"
    fun profileDetail(id: Int) = "profile/$id"
}