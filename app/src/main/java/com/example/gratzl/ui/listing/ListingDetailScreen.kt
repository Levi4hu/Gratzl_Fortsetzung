package com.example.gratzl.ui.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.shared.components.UserAvatar
import com.example.gratzl.shared.components.UrgencyChip
import com.example.gratzl.shared.components.getImageUrlForListing
import com.example.gratzl.shared.theme.MarktplatzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingDetailScreen(
    listingId: Int,
    onNavigateToChat: (Int) -> Unit,
    onNavigateToProfile: (Int) -> Unit, // neu von Greta eingefügt
    onNavigateBack: () -> Unit,
    viewModel: ListingDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showReportDialog by remember { mutableStateOf(false) }

    LaunchedEffect(listingId) {
        viewModel.loadListing(listingId)
    }

    val listing = state.listing
    val user    = state.user

    if (showReportDialog) {
        AlertDialog(
            onDismissRequest = { showReportDialog = false },
            title = { Text("Anzeige melden") },
            text = { Text("Möchtest du diese Anzeige wirklich melden?") },
            dismissButton = {
                OutlinedButton(onClick = { showReportDialog = false }) {
                    Text("Abbrechen")
                }
            },
            confirmButton = {
                Button(onClick = { showReportDialog = false }) {
                    Text("Melden")
                }
            }
        )
    }

    if (listing == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    //Spacer(modifier = Modifier.height(2.dp))
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = listing.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Zurück")
                    }
                },
                actions = {
                    IconButton(onClick = { showReportDialog = true }) {
                        Icon(Icons.Filled.Flag, contentDescription = "Anzeige melden")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            val chat = com.example.gratzl.data.model.SampleData
                .getChatForListing(listing.id)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Platz für linken Button (1/5)
                Box(modifier = Modifier.weight(0.5f))
                IconButton(
                    onClick  = { viewModel.toggleFavorite() },
                    //modifier = Modifier.align(bottomEnd)
                ) {
                    Icon(
                        imageVector = if (state.isFavorite)
                            Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorit",
                        tint = if (state.isFavorite) Color.Red
                        else MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Button(
                    onClick  = { chat?.let { onNavigateToChat(it.id) } },
                    modifier = Modifier.weight(15f)
                ) {
                    Text("Nachricht senden")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Bild-Platzhalter // Rounden
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model              = getImageUrlForListing(listing),
                    contentDescription = listing.category,
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.15f))
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {

                // Titel
                Text(
                    listing.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(2.dp))

                // User Info
                if (user != null) {
                    Row(
                        modifier = Modifier.clickable {
                            user?.let { onNavigateToProfile(it.id) }
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        UserAvatar(user = user, size = 45.dp)
                        Text(user.name, style = MaterialTheme.typography.bodySmall)
                        Text("·", style = MaterialTheme.typography.bodySmall)
                        Text(
                            "★ ${user.rating}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

                //Spacer(modifier = Modifier.height(-3.dp)) Unter PB ? gerade rausgelöscht weil zu viel

                // Tags
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    UrgencyChip(urgency = listing.urgency)
                    SuggestionChip(
                        onClick = {},
                        label   = {
                            Text(
                                when (listing.priceType) {
                                    PriceType.FREE     -> "Kostenlos"
                                    PriceType.TRADE    -> "Tausch"
                                    PriceType.COFFEE   -> "Kaffee"
                                    PriceType.PER_HOUR -> "${listing.price?.toInt()} € / Std"
                                    PriceType.FIXED    -> "${listing.price?.toInt()} €"
                                }
                            )
                        }
                    )
                    SuggestionChip(
                        onClick = {},
                        label   = { Text(listing.district) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Beschreibung
                Text(
                    listing.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListingDetailScreenPreview() {
    MarktplatzTheme {
        ListingDetailScreen(
            listingId        = 1,
            onNavigateToChat = {},
            onNavigateToProfile = {},
            onNavigateBack   = {}
        )
    }
}