package com.example.gratzl.ui.listing

import androidx.lifecycle.ViewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ListingDetailUiState(
    val listing: Listing? = null,
    val user: UserProfile? = null,
    val isFavorite: Boolean = false
)

class ListingDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ListingDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadListing(id: Int) {
        val listing = SampleData.getListingById(id)
        val user    = listing?.let { SampleData.getUserById(it.userId) }
        _uiState.update { it.copy(listing = listing, user = user) }
    }

    fun toggleFavorite() {
        _uiState.update { it.copy(isFavorite = !it.isFavorite) }
    }
}