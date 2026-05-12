package com.example.gratzl.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListingDetailUiState(
    val listing: Listing? = null,
    val user: UserProfile? = null,
    val isFavorite: Boolean = false
)

class ListingDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ListingDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            SampleData.favouriteIds.collect { favs ->
                val id = _uiState.value.listing?.id
                if (id != null) {
                    _uiState.update { it.copy(isFavorite = id in favs) }
                }
            }
        }
    }

    fun loadListing(id: Int) {
        val listing = SampleData.getListingById(id)
        val user    = listing?.let { SampleData.getUserById(it.userId) }
        val isFav   = id in SampleData.favouriteIds.value
        _uiState.update { it.copy(listing = listing, user = user, isFavorite = isFav) }
    }

    fun toggleFavorite() {
        val id = _uiState.value.listing?.id ?: return
        SampleData.toggleFavourite(id)
    }
}