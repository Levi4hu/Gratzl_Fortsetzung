package com.example.gratzl.ui.search

import androidx.lifecycle.ViewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SearchUiState(
    val query: String = "",
    val results: List<Listing> = SampleData.listings,
    val isOfferMode: Boolean = true,
    val selectedCategory: String? = null,
    val selectedBezirk: String = "Wien"
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

    fun toggleMode(isOffer: Boolean) {
        _uiState.update { it.copy(isOfferMode = isOffer) }
        applyFilters()
    }

    fun onCategorySelected(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val districtName = state.selectedBezirk
            .substringAfter("· ")
            .trim()
        val filtered = SampleData.listings
            .filter { it.isOffer == state.isOfferMode }
            .filter { state.selectedBezirk == "Wien" || it.district.contains(districtName, ignoreCase = true) }
            .filter { state.query.isBlank() || it.title.contains(state.query, ignoreCase = true) }
            .filter { state.selectedCategory == null || it.category == state.selectedCategory }
        _uiState.update { it.copy(results = filtered) }
    }
}