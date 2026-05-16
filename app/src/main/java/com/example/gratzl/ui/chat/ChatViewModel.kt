package com.example.gratzl.ui.chat

import androidx.lifecycle.ViewModel
import com.example.gratzl.data.model.Chat
import com.example.gratzl.data.model.ChatMessage
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val CurrentUserId = 1

data class ChatListItemUi(
    val chat: Chat,
    val listing: Listing,
    val partner: UserProfile,
    val lastMessage: ChatMessage?,
    val isCurrentUserListing: Boolean
)

data class ChatListUiState(
    val query: String = "",
    val isOfferMode: Boolean = true,
    val chats: List<ChatListItemUi> = emptyList()
)

enum class ReportReason(val label: String) {
    SUSPICIOUS("Verdächtiges Verhalten"),
    HARASSMENT("Belästigung"),
    INSULT("Beleidigung"),
    INAPPROPRIATE("Unangemessene Inhalte"),
    OTHER("Andere Gründe")
}

data class ChatDetailUiState(
    val chat: Chat? = null,
    val listing: Listing? = null,
    val partner: UserProfile? = null,
    val messageText: String = "",
    val currentUserId: Int = CurrentUserId,
    val showRequestDialog: Boolean = false,
    val requestDate: String = "",
    val requestPrice: String = "",
    val requestDetails: String = "",
    val requestDateError: String? = null,
    val requestPriceError: String? = null,
    val requestDetailsError: String? = null,
    val showReportScreen: Boolean = false,
    val selectedReportReason: ReportReason? = null,
    val reportDetails: String = ""
)

class ChatViewModel : ViewModel() {
    private val _listUiState = MutableStateFlow(ChatListUiState())
    val listUiState = _listUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow(ChatDetailUiState())
    val detailUiState = _detailUiState.asStateFlow()

    init {
        applyChatFilters()
    }

    fun onSearchChange(value: String) {
        _listUiState.update { it.copy(query = value) }
        applyChatFilters()
    }

    fun toggleMode(isOffer: Boolean) {
        _listUiState.update { it.copy(isOfferMode = isOffer) }
        applyChatFilters()
    }

    fun loadChat(chatId: Int) {
        updateLoadedChat(chatId)
    }

    fun onMessageChange(value: String) {
        _detailUiState.update { it.copy(messageText = value) }
    }

    fun sendMessage() {
        val state = _detailUiState.value
        val chat = state.chat ?: return
        val text = state.messageText.trim()
        if (text.isBlank()) return

        SampleData.addMessageToChat(
            chatId = chat.id,
            message = ChatMessage(
                id = nextMessageId(),
                chatId = chat.id,
                senderId = CurrentUserId,
                text = text,
                timestamp = "Jetzt"
            )
        )

        _detailUiState.update { it.copy(messageText = "") }
        updateLoadedChat(chat.id)
    }

    fun openRequestDialog() {
        _detailUiState.update {
            it.copy(
                showRequestDialog = true,
                requestDateError = null,
                requestPriceError = null,
                requestDetailsError = null
            )
        }
    }

    fun closeRequestDialog() {
        _detailUiState.update {
            it.copy(
                showRequestDialog = false,
                requestDateError = null,
                requestPriceError = null,
                requestDetailsError = null
            )
        }
    }

    fun onRequestDateChange(value: String) {
        _detailUiState.update { it.copy(requestDate = value, requestDateError = null) }
    }

    fun onRequestPriceChange(value: String) {
        val filtered = value.filter { it.isDigit() || it == ',' || it == '.' }.take(8)
        _detailUiState.update { it.copy(requestPrice = filtered, requestPriceError = null) }
    }

    fun onRequestDetailsChange(value: String) {
        _detailUiState.update {
            it.copy(
                requestDetails = value.take(300),
                requestDetailsError = null
            )
        }
    }

    fun sendRequest() {
        val state = _detailUiState.value
        val chat = state.chat ?: return
        val date = state.requestDate.trim()
        val price = state.requestPrice.trim()
        val details = state.requestDetails.trim()
        val numericPrice = price.replace(',', '.').toDoubleOrNull()

        val dateError = if (date.isBlank()) "Datum ist erforderlich" else null
        val priceError = when {
            price.isBlank() -> "Preis ist erforderlich"
            numericPrice == null || numericPrice <= 0.0 -> "Bitte gültigen Preis eingeben"
            else -> null
        }
        val detailsError = when {
            details.isBlank() -> "Details sind erforderlich"
            details.length < 10 -> "Mindestens 10 Zeichen"
            else -> null
        }

        if (dateError != null || priceError != null || detailsError != null) {
            _detailUiState.update {
                it.copy(
                    requestDateError = dateError,
                    requestPriceError = priceError,
                    requestDetailsError = detailsError
                )
            }
            return
        }

        SampleData.addMessageToChat(
            chatId = chat.id,
            message = ChatMessage(
                id = nextMessageId(),
                chatId = chat.id,
                senderId = CurrentUserId,
                text = "Anfrage für $date · $price €\n$details",
                timestamp = "Jetzt",
                isBookingOffer = true
            )
        )

        _detailUiState.update {
            it.copy(
                showRequestDialog = false,
                requestDate = "",
                requestPrice = "",
                requestDetails = "",
                requestDateError = null,
                requestPriceError = null,
                requestDetailsError = null
            )
        }
        updateLoadedChat(chat.id)
    }

    fun openReportScreen() {
        _detailUiState.update { it.copy(showReportScreen = true) }
    }

    fun closeReportScreen() {
        _detailUiState.update {
            it.copy(
                showReportScreen = false,
                selectedReportReason = null,
                reportDetails = ""
            )
        }
    }

    fun onReportReasonSelected(reason: ReportReason) {
        _detailUiState.update { it.copy(selectedReportReason = reason) }
    }

    fun onReportDetailsChange(value: String) {
        _detailUiState.update { it.copy(reportDetails = value.take(400)) }
    }

    fun sendReport() {
        if (_detailUiState.value.selectedReportReason == null) return
        closeReportScreen()
    }

    private fun applyChatFilters() {
        val state = _listUiState.value
        val query = state.query.trim()

        val chats = SampleData.chats.mapNotNull { chat ->
            val listing = SampleData.getListingById(chat.listingId) ?: return@mapNotNull null
            val partner = SampleData.getUserById(chat.partnerId) ?: return@mapNotNull null
            val lastMessage = chat.messages.lastOrNull()

            ChatListItemUi(
                chat = chat,
                listing = listing,
                partner = partner,
                lastMessage = lastMessage,
                isCurrentUserListing = listing.userId == CurrentUserId
            )
        }.filter { item ->
            item.listing.isOffer == state.isOfferMode
        }.filter { item ->
            query.isBlank() ||
                item.listing.title.contains(query, ignoreCase = true) ||
                item.partner.name.contains(query, ignoreCase = true) ||
                (item.lastMessage?.text?.contains(query, ignoreCase = true) == true)
        }

        _listUiState.update { it.copy(chats = chats) }
    }

    private fun updateLoadedChat(chatId: Int) {
        val chat = SampleData.getChatById(chatId)
        val listing = chat?.let { SampleData.getListingById(it.listingId) }
        val partner = chat?.let { SampleData.getUserById(it.partnerId) }

        _detailUiState.update {
            it.copy(
                chat = chat,
                listing = listing,
                partner = partner
            )
        }
    }

    private fun nextMessageId(): Int {
        return SampleData.chats
            .flatMap { it.messages }
            .maxOfOrNull { it.id }
            ?.plus(1) ?: 1
    }
}
