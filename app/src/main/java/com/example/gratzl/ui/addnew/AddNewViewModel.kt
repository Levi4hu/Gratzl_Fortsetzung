package com.example.gratzl.ui.addnew

import androidx.lifecycle.ViewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.UrgencyTag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddNewUiState(
    val title: String = "",
    val description: String = "",
    val category: String? = null,
    val isOffer: Boolean = true,
    val priceType: PriceType = PriceType.FREE,
    val price: String = "",
    val urgency: UrgencyTag = UrgencyTag.FLEXIBLE,
    val titleError: String? = null,
    val isSubmitted: Boolean = false
)

class AddNewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddNewUiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value, titleError = null) }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(description = value) }
    }

    fun onCategorySelected(value: String) {
        _uiState.update { it.copy(category = value) }
    }

    fun onIsOfferChange(value: Boolean) {
        _uiState.update { it.copy(isOffer = value) }
    }

    fun onPriceTypeSelected(value: PriceType) {
        _uiState.update { it.copy(priceType = value) }
    }

    fun onPriceChange(value: String) {
        _uiState.update { it.copy(price = value) }
    }

    fun onUrgencySelected(value: UrgencyTag) {
        _uiState.update { it.copy(urgency = value) }
    }

    fun onSubmit() {
        val state = _uiState.value

        // Validierung
        if (state.title.length < 10) {
            _uiState.update { it.copy(titleError = "Titel muss mindestens 10 Zeichen haben") }
            return
        }

        // Neues Listing erstellen
        val newListing = Listing(
            id          = SampleData.listings.size + 1,
            title       = state.title,
            description = state.description,
            isOffer     = state.isOffer,
            category    = state.category ?: "Sonstiges",
            district    = "Wien",
            priceType   = state.priceType,
            price       = state.price.toDoubleOrNull(),
            urgency     = state.urgency,
            userId      = 1
        )

        // Zu SampleData hinzufügen
        SampleData.listings.add(newListing)

        // Erfolg signalisieren
        _uiState.update { it.copy(isSubmitted = true) }
    }

    fun resetSubmit() {
        _uiState.update { it.copy(isSubmitted = false) }
    }
}