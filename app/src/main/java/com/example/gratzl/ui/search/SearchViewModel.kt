package com.example.gratzl.ui.search

import androidx.lifecycle.ViewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.ui.home.HomeFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class PriceSortOrder { NONE, ASC, DESC }
enum class DistanceFilter { ALL, NEAR_1KM, NEAR_3KM, NEAR_5KM }

data class SearchUiState(
    val query: String = "",
    val results: List<Listing> = SampleData.listings,
    val filter: HomeFilter = HomeFilter.ALL,
    val selectedCategory: String? = null,
    val selectedBezirk: String = "Wien",
    val offerCount: Int = 0,
    val requestCount: Int = 0,
    val allCount: Int = 0,
    val favourites: Set<Int> = emptySet(),
    val priceSortOrder: PriceSortOrder = PriceSortOrder.NONE,
    val distanceFilter: DistanceFilter = DistanceFilter.ALL
)

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init { applyFilters() }

    fun onBezirkChange(bezirk: String) {
        _uiState.update { it.copy(selectedBezirk = bezirk) }
        applyFilters()
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
        applyFilters()
    }

    fun onFilterChange(filter: HomeFilter) {
        _uiState.update { it.copy(filter = filter) }
        applyFilters()
    }

    fun onCategorySelected(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
        applyFilters()
    }

    fun onPriceSortOrderChange(order: PriceSortOrder) {
        _uiState.update { it.copy(priceSortOrder = order) }
        applyFilters()
    }

    fun onDistanceFilterChange(distance: DistanceFilter) {
        _uiState.update { it.copy(distanceFilter = distance) }
        applyFilters()
    }

    fun toggleFavourite(id: Int) {
        _uiState.update {
            val favs = if (id in it.favourites) it.favourites - id else it.favourites + id
            it.copy(favourites = favs)
        }
    }

    private fun applyFilters() {
        val state = _uiState.value
        val districtName = state.selectedBezirk.substringAfter("· ").trim()
        val districtListings = if (state.selectedBezirk == "Wien") {
            SampleData.listings
        } else {
            SampleData.listings.filter { it.district.contains(districtName, ignoreCase = true) }
        }

        val allCount     = districtListings.size
        val offerCount   = districtListings.count { it.isOffer }
        val requestCount = districtListings.count { !it.isOffer }

        var filtered = districtListings
            .filter {
                when (state.filter) {
                    HomeFilter.ALL      -> true
                    HomeFilter.ANGEBOTE -> it.isOffer
                    HomeFilter.ANFRAGEN -> !it.isOffer
                }
            }
            .filter { state.query.isBlank() || it.title.contains(state.query, ignoreCase = true) }
            .filter { state.selectedCategory == null || it.category == state.selectedCategory }

        // fake distance filter — nimmt einfach die ersten N Ergebnisse
        filtered = when (state.distanceFilter) {
            DistanceFilter.ALL      -> filtered
            DistanceFilter.NEAR_1KM -> filtered.take((filtered.size * 0.3f).toInt().coerceAtLeast(1))
            DistanceFilter.NEAR_3KM -> filtered.take((filtered.size * 0.6f).toInt().coerceAtLeast(1))
            DistanceFilter.NEAR_5KM -> filtered.take((filtered.size * 0.85f).toInt().coerceAtLeast(1))
        }

        filtered = when (state.priceSortOrder) {
            PriceSortOrder.NONE -> filtered
            PriceSortOrder.ASC  -> filtered.sortedWith(compareBy { it.price ?: 0f })
            PriceSortOrder.DESC -> filtered.sortedWith(compareByDescending { it.price ?: 0f })
        }

        _uiState.update {
            it.copy(
                results      = filtered,
                allCount     = allCount,
                offerCount   = offerCount,
                requestCount = requestCount
            )
        }
    }
}