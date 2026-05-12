package com.example.gratzl.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class HomeFilter { ALL, ANGEBOTE, ANFRAGEN }

data class SavedSearch(
    val id: Int,
    val label: String,
    val categoryIcon: String, // einschalten
    val query: String,
    val category: String?,
    val isOffer: Boolean
)

data class HomeUiState(
    val filter: HomeFilter = HomeFilter.ALL,
    val selectedDistrict: String = "Wien",
    val favourites: Set<Int> = emptySet(),
    val forYou: List<Listing> = emptyList(),
    val nearby: List<Listing> = emptyList(),
    val savedSearches: List<SavedSearch> = listOf(
        SavedSearch(1, "Nachhilfe",   "📚", "Nachhilfe", "Bildung",  true),
        SavedSearch(2, "Umzugshilfe", "🏠", "Umzug",    "Haushalt", false),
        SavedSearch(3, "Handwerk",    "🔧", "",          "Handwerk", true)
    )
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            SampleData.favouriteIds.collect { favs ->
                _uiState.update { it.copy(favourites = favs) }
            }
        }
        applyFilter()
    }

    fun onFilterChange(filter: HomeFilter) {
        _uiState.update { it.copy(filter = filter) }
        applyFilter()
    }

    fun onDistrictSelected(district: String) {
        _uiState.update { it.copy(selectedDistrict = district) }
        applyFilter()
    }

    fun toggleFavourite(listingId: Int) {
        SampleData.toggleFavourite(listingId)
    }

    private fun applyFilter() {
        val state = _uiState.value

        val districtName = state.selectedDistrict
            .substringAfter("· ")
            .trim()

        val districtFiltered = if (state.selectedDistrict == "Wien") {
            SampleData.listings
        } else {
            SampleData.listings.filter {
                it.district.contains(districtName, ignoreCase = true)
            }
        }

        val forYou = districtFiltered.filter {
            when (state.filter) {
                HomeFilter.ALL      -> true
                HomeFilter.ANGEBOTE -> it.isOffer
                HomeFilter.ANFRAGEN -> !it.isOffer
            }
        }

        val nearby = SampleData.listings
            .filter { it.district.contains(districtName, ignoreCase = true) }
            .filter {
                when (state.filter) {
                    HomeFilter.ALL      -> true
                    HomeFilter.ANGEBOTE -> it.isOffer
                    HomeFilter.ANFRAGEN -> !it.isOffer
                }
            }

        _uiState.update { it.copy(forYou = forYou, nearby = nearby) }
    }
}