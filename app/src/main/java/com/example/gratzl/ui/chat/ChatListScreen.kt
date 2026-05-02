package com.example.gratzl.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.shared.components.AppTopBar
import com.example.gratzl.shared.components.Switch
import com.example.gratzl.shared.components.UserAvatar
import com.example.gratzl.shared.theme.MarktplatzTheme

@Composable
fun ChatListScreen(
    onNavigateToChat: (Int) -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val state by viewModel.listUiState.collectAsState()

    Scaffold(
        topBar = { AppTopBar(title = "Chat") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Switch(
                isOfferMode = state.isOfferMode,
                onToggle = { viewModel.toggleMode(it) }
            )

            OutlinedTextField(
                value = state.query,
                onValueChange = { viewModel.onSearchChange(it) },
                placeholder = { Text("Suche Chats...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                singleLine = true,
                shape = RoundedCornerShape(50.dp)
            )

            if (state.chats.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Keine Chats gefunden",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(top = 14.dp, bottom = 16.dp)
                ) {
                    items(
                        items = state.chats,
                        key = { item -> item.chat.id }
                    ) { item ->
                        ChatListRow(
                            item = item,
                            onClick = { onNavigateToChat(item.chat.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatListRow(
    item: ChatListItemUi,
    onClick: () -> Unit
) {
    val time = item.lastMessage?.timestamp.orEmpty()

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        ListItem(
            leadingContent = {
                UserAvatar(user = item.partner, size = 52.dp)
            },
            headlineContent = {
                Text(
                    text = item.listing.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                Column {
                    Text(
                        text = item.lastMessage?.text ?: "Noch keine Nachrichten",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = item.partner.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            trailingContent = {
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatListScreenPreview() {
    MarktplatzTheme {
        ChatListScreen(onNavigateToChat = {})
    }
}
