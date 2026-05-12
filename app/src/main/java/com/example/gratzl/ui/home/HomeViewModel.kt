package com.example.gratzl.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.UrgencyTag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class HomeFilter { ALL, ANGEBOTE, ANFRAGEN }

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

data class HomeUiState(
    val filter: HomeFilter = HomeFilter.ALL,
    val selectedDistrict: String = "Wien",
    val favourites: Set<Int> = emptySet(),
    val forYou: List<Listing> = emptyList(),
    val nearby: List<Listing> = emptyList(),
    val allCount: Int = 0,
    val offerCount: Int = 0,
    val requestCount: Int = 0,
    val savedSearches: List<SavedSearch> = emptyList(),
    val showAddSearchSheet: Boolean = false,
    val newSearchLabel: String = "",
    val newSearchCategory: String? = null,
    val newSearchIsOffer: Boolean = true,
    val newSearchPriceType: PriceType = PriceType.FREE,
    val newSearchUrgency: UrgencyTag = UrgencyTag.FLEXIBLE
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

    fun openAddSearchSheet() {
        _uiState.update { it.copy(
            showAddSearchSheet = true,
            newSearchLabel     = "",
            newSearchCategory  = null,
            newSearchIsOffer   = true,
            newSearchPriceType = PriceType.FREE,
            newSearchUrgency   = UrgencyTag.FLEXIBLE
        )}
    }

    fun closeAddSearchSheet() {
        _uiState.update { it.copy(showAddSearchSheet = false) }
    }

    fun onNewSearchLabelChange(value: String) {
        _uiState.update { it.copy(newSearchLabel = value) }
    }

    fun onNewSearchCategoryChange(value: String?) {
        _uiState.update { it.copy(newSearchCategory = value) }
    }

    fun onNewSearchIsOfferChange(value: Boolean) {
        _uiState.update { it.copy(newSearchIsOffer = value) }
    }

    fun onNewSearchPriceTypeChange(value: PriceType) {
        _uiState.update { it.copy(newSearchPriceType = value) }
    }

    fun onNewSearchUrgencyChange(value: UrgencyTag) {
        _uiState.update { it.copy(newSearchUrgency = value) }
    }

    fun saveNewSearch() {
        val state = _uiState.value
        if (state.newSearchLabel.isBlank()) return

        val icon = when (state.newSearchCategory) {
            "Bildung"  -> "📚"
            "Haushalt" -> "🏠"
            "Handwerk" -> "🔧"
            "Garten"   -> "🌿"
            else       -> "🔍"
        }

        val newSearch = SavedSearch(
            id           = (state.savedSearches.maxOfOrNull { it.id } ?: 0) + 1,
            label        = state.newSearchLabel,
            categoryIcon = icon,
            query        = state.newSearchLabel,
            category     = state.newSearchCategory,
            isOffer      = state.newSearchIsOffer,
            priceType    = state.newSearchPriceType,
            urgency      = state.newSearchUrgency
        )

        _uiState.update { it.copy(
            savedSearches      = it.savedSearches + newSearch,
            showAddSearchSheet = false
        )}
    }

    fun deleteSavedSearch(id: Int) {
        _uiState.update {
            it.copy(savedSearches = it.savedSearches.filter { s -> s.id != id })
        }
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


        val allCount     = districtFiltered.size
        val offerCount   = districtFiltered.count { it.isOffer }
        val requestCount = districtFiltered.count { !it.isOffer }

        _uiState.update { it.copy(
            forYou       = forYou,
            nearby       = nearby,
            allCount     = allCount,
            offerCount   = offerCount,
            requestCount = requestCount
        )}
    }
}