package com.example.gratzl.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.data.model.ChatMessage
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.UserProfile
import com.example.gratzl.shared.components.UserAvatar
import com.example.gratzl.shared.theme.MarktplatzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: Int,
    onNavigateToProfile: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val state by viewModel.detailUiState.collectAsState()

    LaunchedEffect(chatId) {
        viewModel.loadChat(chatId)
    }

    val chat = state.chat
    val listing = state.listing
    val partner = state.partner

    if (state.showRequestDialog) {
        RequestDialog(
            state = state,
            onDateChange = { viewModel.onRequestDateChange(it) },
            onPriceChange = { viewModel.onRequestPriceChange(it) },
            onDetailsChange = { viewModel.onRequestDetailsChange(it) },
            onDismiss = { viewModel.closeRequestDialog() },
            onSend = { viewModel.sendRequest() }
        )
    }

    if (state.showReportDialog) {
        ReportDialog(
            onDismiss = { viewModel.closeReportDialog() },
            onConfirm = { viewModel.closeReportDialog() }
        )
    }

    Scaffold(
        topBar = {
            ChatTopBar(
                title = listing?.title ?: "Chat",
                subtitle = listing?.let { priceLabel(it.priceType, it.price) }.orEmpty(),
                onNavigateBack = onNavigateBack,
                onReportClick = { viewModel.openReportDialog() }
            )
        },
        bottomBar = {
            ChatInputBar(
                messageText = state.messageText,
                onMessageChange = { viewModel.onMessageChange(it) },
                onSendMessage = { viewModel.sendMessage() },
                onRequestClick = { viewModel.openRequestDialog() }
            )
        }
    ) { innerPadding ->
        if (chat == null || partner == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Chat nicht gefunden", style = MaterialTheme.typography.bodyMedium)
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            PartnerCard(
                partner = partner,
                onClick = { onNavigateToProfile(partner.id) }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp)
            ) {
                items(
                    items = chat.messages.asReversed(),
                    key = { message -> message.id }
                ) { message ->
                    MessageBubble(
                        message = message,
                        isMine = message.senderId == state.currentUserId
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopBar(
    title: String,
    subtitle: String,
    onNavigateBack: () -> Unit,
    onReportClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Zurück")
            }
        },
        actions = {
            IconButton(onClick = onReportClick) {
                Icon(Icons.Filled.Flag, contentDescription = "Nutzer melden")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
private fun PartnerCard(
    partner: UserProfile,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UserAvatar(user = partner, size = 52.dp)
            Column {
                Text(
                    text = partner.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "★ ${partner.rating} · ${partner.district}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun MessageBubble(
    message: ChatMessage,
    isMine: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (isMine) 18.dp else 4.dp,
                bottomEnd = if (isMine) 4.dp else 18.dp
            ),
            color = if (isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isMine) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
            tonalElevation = if (isMine) 0.dp else 2.dp,
            shadowElevation = if (isMine) 0.dp else 1.dp,
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                if (message.isBookingOffer) {
                    Text(
                        text = "Anfrage",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isMine) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                    )
                }
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isMine) {
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.78f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
private fun ChatInputBar(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    onRequestClick: () -> Unit
) {
    Surface(
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconButton(
                onClick = onRequestClick,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Anfrage senden",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            OutlinedTextField(
                value = messageText,
                onValueChange = onMessageChange,
                placeholder = { Text("Nachricht schreiben...") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                shape = RoundedCornerShape(50.dp)
            )

            IconButton(
                onClick = onSendMessage,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Nachricht senden",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun RequestDialog(
    state: ChatDetailUiState,
    onDateChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onDetailsChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSend: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Anfrage senden") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = state.requestDate,
                        onValueChange = onDateChange,
                        label = { Text("Datum") },
                        placeholder = { Text("TT.MM.JJJJ") },
                        leadingIcon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
                        singleLine = true,
                        isError = state.requestDateError != null,
                        supportingText = {
                            state.requestDateError?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = state.requestPrice,
                        onValueChange = onPriceChange,
                        label = { Text("Preis") },
                        placeholder = { Text("0,00") },
                        trailingIcon = { Text("€") },
                        singleLine = true,
                        isError = state.requestPriceError != null,
                        supportingText = {
                            state.requestPriceError?.let {
                                Text(it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = state.requestDetails,
                    onValueChange = onDetailsChange,
                    label = { Text("Details") },
                    placeholder = { Text("Beschreibe deinen Auftrag oder hinterlasse Details...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp),
                    minLines = 3,
                    maxLines = 5,
                    isError = state.requestDetailsError != null,
                    supportingText = {
                        Text(
                            text = state.requestDetailsError ?: "${state.requestDetails.length}/300",
                            color = if (state.requestDetailsError != null) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                )
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Abbrechen")
            }
        },
        confirmButton = {
            Button(onClick = onSend) {
                Text("Senden")
            }
        }
    )
}

@Composable
private fun ReportDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nutzer melden") },
        text = {
            Text("Möchtest du diesen Chat wirklich melden?")
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Abbrechen")
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Melden")
            }
        }
    )
}

private fun priceLabel(type: PriceType, price: Double?): String {
    return when (type) {
        PriceType.FREE -> "Kostenlos"
        PriceType.TRADE -> "Tausch"
        PriceType.COFFEE -> "Kaffee"
        PriceType.PER_HOUR -> "${price?.toInt() ?: 0}€ / Std"
        PriceType.FIXED -> "${price?.toInt() ?: 0}€"
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    MarktplatzTheme {
        ChatScreen(
            chatId = 1,
            onNavigateToProfile = {},
            onNavigateBack = {}
        )
    }
}
